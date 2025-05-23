package com.ruoyi.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.admin.domain.TApplyApproval;
import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.bo.DxfByTargetBo;
import com.ruoyi.admin.domain.bo.PublishBo;
import com.ruoyi.admin.domain.bo.PublishLayerBo;
import com.ruoyi.admin.domain.enumeration.ApplyState;
import com.ruoyi.admin.mapper.TApplyApprovalMapper;
import com.ruoyi.admin.mapper.TLayerMapper;
import com.ruoyi.admin.service.ITLayerService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.system.mapper.SysUserMapper;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.ShpToDxfConverterWithGdalJNI;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.util.GeoServerHttpClient;
import lombok.extern.slf4j.Slf4j;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图层Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-08
 */
@Service
@Slf4j
public class TLayerServiceImpl implements ITLayerService {
    @Autowired
    private TLayerMapper tLayerMapper;

    @Autowired
    private TApplyApprovalMapper tApplyApprovalMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    @Value("${layer.webUrl}")
    private String webUrl;
    @Value("${layer.savePath}")
    private String savePath;
    @Value("${layer.workspace}")
    private String workspace;
    @Value("${layer.datastore}")
    private String datastore;

    /**
     * 查询图层
     *
     * @param id 图层主键
     * @return 图层
     */
    @Override
    public TLayer selectTLayerById(Long id) {
        return tLayerMapper.selectTLayerById(id);
    }

    /**
     * 查询图层列表
     *
     * @param tLayer 图层
     * @return 图层
     */
    @Override
    public List<TLayer> selectTLayerList(TLayer tLayer) {
        Long baseId = tLayer.getBaseId();
        System.out.println("基地id：" + baseId);
        Long[] baseIds;
        if (null == baseId) {
            baseIds = SecurityUtils.getBaseId();
        } else {
            baseIds = new Long[]{baseId};
        }
        if (0 < baseIds.length)
            tLayer.setBaseIds(baseIds);
        return tLayerMapper.selectTLayerList(tLayer);
    }

    /**
     * 新增图层
     *
     * @param tLayer 图层
     * @return 结果
     */
    @Override
    public int insertTLayer(TLayer tLayer) {
        return tLayerMapper.insertTLayer(tLayer);
    }

    /**
     * 修改图层
     *
     * @param tLayer 图层
     * @return 结果
     */
    @Override
    public int updateTLayer(TLayer tLayer) {
        return tLayerMapper.updateTLayer(tLayer);
    }

    /**
     * 批量删除图层
     *
     * @param ids 需要删除的图层主键
     * @return 结果
     */
    @Override
    public int deleteTLayerByIds(Long[] ids) {
        return tLayerMapper.deleteTLayerByIds(ids);
    }

    /**
     * 删除图层信息
     *
     * @param id 图层主键
     * @return 结果
     */
    @Override
    public int deleteTLayerById(Long id) {
        return tLayerMapper.deleteTLayerById(id);
    }

    @Override
    public AjaxResult getDxfByTarget(DxfByTargetBo bo) {
        try {
            String target = "";
            for (String xy : bo.getTagert()) {
                target += target.length() > 0 ? "," + xy : xy;
            }
            if (0 < bo.getTagert().length) {
                target += "," + bo.getTagert()[0];
            }
            List<TLayer> layerVoList = null;
            if (null != bo.getIds() && 0 < bo.getIds().length) {
                layerVoList = tLayerMapper.selectTLayerByIds(bo.getIds());
            } else {
                TLayer layer = new TLayer();
                layerVoList = tLayerMapper.selectTLayerListGroupLayerParam(layer);
            }
            if (!layerVoList.isEmpty()) {
                List<String> data = new ArrayList<>();
                for (TLayer tLayer : layerVoList) {
                    try {
                        if (null != tLayer.getLayerParam() && !"".equals(tLayer.getLayerParam())) {
                            String layerParamArr[] = tLayer.getLayerParam().split("=");
                            layerParamArr = layerParamArr[1].split(":");
                            data.addAll(tLayerMapper.getIntersection(layerParamArr[1], target));
                        }
                    } catch (Exception e) {
                        System.out.println("跳过异常：" + tLayer.toString());
                    }
                }

//                for (TLayer tLayer : layerVoList) {
//                    if (null != tLayer.getLayerParam() && !"".equals(tLayer.getLayerParam())) {
//                        String layerParamArr[] = tLayer.getLayerParam().split(",");
//                        layerParamArr = layerParamArr[0].split("=");
//                        List<String> wtkData = GeoServerToDXF.getWPSDataFromGeoServer(layerParamArr[1], target);
//                        if (null != wtkData && !wtkData.isEmpty())
//                            data.addAll(wtkData);
//                    }
//                }
//                List<String> wtkData = GeoServerToDXF.getWPSDataFromGeoServer("ne:DLGX_X_JSONToFeatures", target);
//                if (null != wtkData && !wtkData.isEmpty())
//                    data.addAll(wtkData);
                data = CollUtil.distinct(data);
                System.out.println("查询到的数据：" + data.size());
                if (!data.isEmpty()) {
                    String userDir = savePath + "/layer/";
                    FileUtils.checkDirAndCreate(userDir);
                    String fileName = "layer_" + System.currentTimeMillis();
                    String filePath = userDir + fileName + ".shp";
                    String webPath = webUrl + fileName + ".dxf";
                    String result = ShpToDxfConverterWithGdalJNI.createShapefile(data, filePath);
                    System.out.println("创建的shp文件路径：" + result);
                    if (result != null) {
                        AjaxResult ajaxResult = ShpToDxfConverterWithGdalJNI.convertShpToDxf(result, userDir + fileName + ".dxf", webPath);
                        System.out.println(ajaxResult.toString());
                        if (ajaxResult.isSuccess()) {
                            String fileUrl = (String) ajaxResult.get("data");

                            //向申请审批表插入一条带审批记录，并且返回“操作成功，等待审核”
                            TApplyApproval approval = new TApplyApproval();
                            approval.setName("申请区域DXF文件");
                            approval.setDeptId(SecurityUtils.getDeptId());
                            approval.setUserId(SecurityUtils.getUserId());
                            approval.setState(ApplyState.PENDING.getCode());

                            //将 DxfByTargetBo 转换为 JSON
                            String paramJson = JSONUtil.toJsonStr(bo.getTagert());

                            approval.setParam(paramJson);
                            approval.setFileUrl(fileUrl);

                            int row = tApplyApprovalMapper.insertTApplyApproval(approval);
                            if (0 < row) {
                                return AjaxResult.success("操作成功，等待审核");
                            }
                        }
                    }
                } else {
                    return AjaxResult.error("无数据");
                }
            } else {
                return AjaxResult.error("图层不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("裁剪失败：{}", e.getMessage());
        }
        return AjaxResult.error("获取失败");
    }

    @Override
    public List<GenTable> getLayerTable() {
        return tLayerMapper.getLayerTable();
    }

    @Override
    public AjaxResult publishLayer(PublishBo bo) {
        String newWorkspace = bo.getWorkspace();
        String newDatastore = bo.getDatastore();
        if (ObjUtil.isEmpty(newWorkspace)) newWorkspace = workspace;
        if (ObjUtil.isEmpty(newDatastore)) newDatastore = datastore;
        List<TLayer> layerList = new ArrayList<>();
        Long baseId = bo.getBaseId();
        System.out.println("基地id：" + baseId);
        Long[] baseIds;
        if (null == baseId) {
            baseIds = SecurityUtils.getBaseId();
        } else {
            baseIds = new Long[]{baseId};
        }
        for (PublishLayerBo publishLayerBo : bo.getPublishLayer()) {
            if (null == publishLayerBo.getName() || null == publishLayerBo.getCode()
                    || null == publishLayerBo.getTableName() || null == publishLayerBo.getParam())
                return AjaxResult.error("参数异常");
            if (null == publishLayerBo.getStyle())
                publishLayerBo.setStyle("point");
            for (Long id : baseIds) {
                TLayer layer = new TLayer();
                layer.setName(publishLayerBo.getName());
                layer.setTableName(publishLayerBo.getTableName());
                layer.setCode(publishLayerBo.getCode());
                layer.setLayerParam(publishLayerBo.getParam());
                layer.setBaseId(id);
                layer.setIsShow(false);
                layer.setServerType("1");
                layer.setPid(0L);
                layer.setUrl("http://124.221.121.154:8081/geoserver/" + newWorkspace + "/wms");
                layerList.add(layer);
            }
        }
        if (GeoServerHttpClient.publishLyaerList(
                bo.getPublishLayer(), // 发布的数据
                newWorkspace, // 工作区名称
                newDatastore, // 数据存储名称
                bo.isForcePublish() // 是否覆盖并重新发布
        )) {
            for (TLayer layer : layerList) {
                List<TLayer> tLayerList = tLayerMapper.selectTLayerList(layer);
                if (tLayerList.isEmpty())
                    tLayerMapper.insertTLayer(layer);
            }
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getAllSpatialRefSystems(){
        return AjaxResult.success(tLayerMapper.getAllSpatialRefSystems());
    }


    @Override
    public List<String> getIntersectionExt( String tableName,
                                      String targetGeoJson,
                                            Integer  srid){

        // 打印参数确认
        System.out.println("Parameters: tableName=" + tableName + ", targetGeoJson=" + targetGeoJson + ", srid=" + srid);
        return tLayerMapper.getIntersectionExt(tableName,targetGeoJson,srid);
    }
}
