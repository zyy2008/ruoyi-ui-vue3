package com.ruoyi.admin.service.impl;

import com.ruoyi.admin.domain.TLayer;
import com.ruoyi.admin.domain.TLayerSet;
import com.ruoyi.admin.domain.TLayerSetLayer;
import com.ruoyi.admin.domain.TMapLabel;
import com.ruoyi.admin.domain.bo.MapLabelShareBo;
import com.ruoyi.admin.mapper.TLayerMapper;
import com.ruoyi.admin.mapper.TLayerSetLayerMapper;
import com.ruoyi.admin.mapper.TLayerSetMapper;
import com.ruoyi.admin.mapper.TMapLabelMapper;
import com.ruoyi.admin.service.ITMapLabelService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.system.domain.SysUserBase;
import com.ruoyi.common.system.mapper.SysUserBaseMapper;
import com.ruoyi.common.system.service.ISysUserService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图标注Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-10
 */
@Service
public class TMapLabelServiceImpl implements ITMapLabelService {
    @Autowired
    private TMapLabelMapper tMapLabelMapper;
    @Autowired
    private TLayerMapper tLayerMapper;
    @Autowired
    private TLayerSetMapper tLayerSetMapper;
    @Autowired
    private TLayerSetLayerMapper tLayerSetLayerMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

    /**
     * 查询地图标注
     *
     * @param id 地图标注主键
     * @return 地图标注
     */
    @Override
    public TMapLabel selectTMapLabelById(Long id) {
        return tMapLabelMapper.selectTMapLabelById(id);
    }

    /**
     * 查询地图标注列表
     *
     * @param tMapLabel 地图标注
     * @return 地图标注
     */
    @Override
    public List<TMapLabel> selectTMapLabelList(TMapLabel tMapLabel) {
        return tMapLabelMapper.selectTMapLabelList(tMapLabel);
    }

    /**
     * 新增地图标注
     *
     * @param tMapLabel 地图标注
     * @return 结果
     */
    @Override
    public int insertTMapLabel(TMapLabel tMapLabel) {
        tMapLabel.setUserId(SecurityUtils.getUserId());
        tMapLabel.setDeptId(SecurityUtils.getDeptId());
//        TLayerSet layerSet = tLayerSetMapper.selectTLayerSetById(tMapLabel.getLayerSetId());
//        if (null == layerSet) throw new ServiceException("图层集不存在");
//        tMapLabel.setBaseId(layerSet.getBaseId());
        return tMapLabelMapper.insertTMapLabel(tMapLabel);
    }

    /**
     * 修改地图标注
     *
     * @param tMapLabel 地图标注
     * @return 结果
     */
    @Override
    public int updateTMapLabel(TMapLabel tMapLabel) {
//        TLayerSet layerSet = tLayerSetMapper.selectTLayerSetById(tMapLabel.getLayerSetId());
//        if (null == layerSet) throw new ServiceException("图层集不存在");
//        tMapLabel.setBaseId(layerSet.getBaseId());
        int row = tMapLabelMapper.updateTMapLabel(tMapLabel);
        if (row > 0) {
            List<TMapLabel> tMapLabelList = tMapLabelMapper.selectTMapLabelListByMapLabelId(tMapLabel.getId());
            System.out.println(tMapLabel.getId() + "关联分享：" + tMapLabelList.size());
            for (TMapLabel mapLabel : tMapLabelList) {
                mapLabel.setLayerSetId(tMapLabel.getLayerSetId());
                mapLabel.setName(tMapLabel.getName());
                mapLabel.setData(tMapLabel.getData());
                mapLabel.setDataType(tMapLabel.getDataType());
                mapLabel.setView(tMapLabel.getView());
                tMapLabelMapper.updateTMapLabel(mapLabel);
            }
        }
        return row;
    }

    /**
     * 批量删除地图标注
     *
     * @param ids 需要删除的地图标注主键
     * @return 结果
     */
    @Override
    public int deleteTMapLabelByIds(Long[] ids) {
//        for (Long id : ids) {
//            deleteLayerSetAndLayer(id);
//        }
        return tMapLabelMapper.deleteTMapLabelByIds(ids);
    }

    /**
     * 删除地图标注信息
     *
     * @param id 地图标注主键
     * @return 结果
     */
    @Override
    public int deleteTMapLabelById(Long id) {
//        deleteLayerSetAndLayer(id);
        return tMapLabelMapper.deleteTMapLabelById(id);
    }

    /**
     * 删除图层集和图层
     */
    public void deleteLayerSetAndLayer(Long mapLabelId) {
        if (null == mapLabelId) {
            System.out.println("mapLabelId参数为空");
            return;
        }
        TMapLabel mapLabel = tMapLabelMapper.selectTMapLabelById(mapLabelId);
        if (null == mapLabel) {
            System.out.println(mapLabelId + "地图标注数据不存在");
            return;
        }
        System.out.println("标注数据：" + mapLabel);
        Long layerSetId = mapLabel.getLayerSetId();
        TLayerSet layerSet = tLayerSetMapper.selectTLayerSetById(layerSetId);
        if (null == layerSet) {
            System.out.println(layerSetId + "图层集不存在");
            return;
        }
        System.out.println("图层集：" + layerSet);
        TLayerSetLayer layerSetLayer = new TLayerSetLayer();
        layerSetLayer.setLayerSetId(layerSetId);
        List<TLayerSetLayer> layerSetLayerList = tLayerSetLayerMapper.selectTLayerSetLayerList(layerSetLayer);
        Long[] layerIdArr = new Long[layerSetLayerList.size()];
        for (int i = 0; i < layerSetLayerList.size(); i++) {
            layerIdArr[i] = layerSetLayerList.get(i).getLayerId();
        }
        if (null != mapLabel.getMapLabelId() && null != layerIdArr && 0 < layerIdArr.length)
            tLayerMapper.deleteTLayerByIds(layerIdArr);
        tLayerSetMapper.deleteTLayerSetById(layerSet.getId());
        tMapLabelMapper.deleteTLayerSetByMapLabelId(mapLabelId);
    }

    /**
     * 分享图层集
     */
    @Override
    public R<Void> share(MapLabelShareBo bo) {
        try {
            for (Long labelId : bo.getMapLabelId()) {
                TMapLabel mapLabel = tMapLabelMapper.selectTMapLabelById(labelId);
                if (null == mapLabel) return R.fail(labelId + "地图标注不存在");
                Long mapLabelId = mapLabel.getId();
                if (null != mapLabel.getMapLabelId()) return R.fail(labelId + "该地图标注不支持分享");
               // List<TLayerSet> layerSetList = tLayerSetMapper.selectTLayerSetListByDeptIds(bo.getThisDeptId());
              //  if (layerSetList.isEmpty()) return R.fail(mapLabel.getLayerSetId() + "图层集不存在");
                List<SysUser> sysUserList = new ArrayList<>();
                if (null != bo.getDeptId() && 0 < bo.getDeptId().length) {
                    sysUserList.addAll(sysUserService.selectUserListByDeptId(bo.getDeptId()));
                }
                if (null != bo.getUserId() && 0 < bo.getUserId().length) {
                    for (Long userId : bo.getUserId()) {
                        SysUser sysUser = sysUserService.selectUserById(userId);
                        if (null == sysUser) return R.fail("用户不存在：" + userId);
                        sysUserList.add(sysUser);
                    }
                }
                if (sysUserList.isEmpty()) return R.fail("没有人员可以被分享");
                //for (TLayerSet layerSet : layerSetList) {
                    //Long layerSetId = layerSet.getId();
                   // List<TLayer> layerList = tLayerMapper.selectTLayerBySetIdList(layerSet.getId());
                    for (SysUser sysUser : sysUserList) {
                        SysUserBase sysUserBase = new SysUserBase();
                        sysUserBase.setUserId(sysUser.getUserId());
                        List<SysUserBase> sysUserBaseList = sysUserBaseMapper.selectSysUserBaseList(sysUserBase);

                        for (SysUserBase userBase : sysUserBaseList) {
                            mapLabel.setId(null);
                            mapLabel.setUserId(sysUser.getUserId());
                            mapLabel.setDeptId(sysUser.getDeptId());
                            mapLabel.setBaseId(userBase.getBaseId());
                            mapLabel.setMapLabelId(mapLabelId);

                            tMapLabelMapper.deleteTMapLabelByNameAndUserIdAndDeptId(mapLabel);
//
//                            layerSet.setId(null);
//                            layerSet.setUserId(sysUser.getUserId());
//                            layerSet.setDeptId(sysUser.getDeptId());
//                            layerSet.setBaseId(userBase.getBaseId());
//                            tLayerSetMapper.insertTLayerSet(layerSet);
//                            System.out.println("添加的图层集：" + layerSet);
//                            mapLabel.setLayerSetId(layerSet.getId());
                            tMapLabelMapper.insertTMapLabel(mapLabel);

                            //TLayerSetLayer layerSetLayer = new TLayerSetLayer();
                            //layerSetLayer.setLayerSetId(layerSetId);
                            //List<TLayerSetLayer> layerSetLayerList = tLayerSetLayerMapper.selectTLayerSetLayerList(layerSetLayer);
                            //Long[] layerIdArr = new Long[layerSetLayerList.size()];
                            //for (int i = 0; i < layerSetLayerList.size(); i++) {
                            //    layerIdArr[i] = layerSetLayerList.get(i).getLayerId();
                            // }
//                            if (null != layerIdArr && 0 < layerIdArr.length) {
//                                for (TLayer tLayer : layerList) {
//                                    tLayer.setId(null);
//                                    tLayer.setUserId(sysUser.getUserId());
//                                    tLayer.setDeptId(sysUser.getDeptId());
//                                    tLayer.setBaseId(userBase.getBaseId());
//                                    List<TLayer> tLayerList = tLayerMapper.selectTLayerList(tLayer);
//                                    if (tLayerList.isEmpty()) {
//                                        tLayerMapper.insertTLayer(tLayer);
//                                        TLayerSetLayer insertLayerSetLayer = new TLayerSetLayer();
//                                        insertLayerSetLayer.setLayerId(tLayer.getId());
//                                        insertLayerSetLayer.setLayerSetId(layerSet.getId());
//                                        tLayerSetLayerMapper.insertTLayerSetLayer(insertLayerSetLayer);
//                                    }
//                                }
//                            }
                        }
                    }
                //}
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail();
        }
    }
}
