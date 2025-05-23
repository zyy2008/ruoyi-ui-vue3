package com.ruoyi.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.admin.domain.*;
import com.ruoyi.admin.domain.bo.AxisBo;
import com.ruoyi.admin.domain.bo.LayerSetShareBo;
import com.ruoyi.admin.domain.bo.ShortestPathBo;
import com.ruoyi.admin.domain.bo.TLayerSetBo;
import com.ruoyi.admin.domain.vo.*;
import com.ruoyi.admin.mapper.*;
import com.ruoyi.admin.service.ITElementClassificationService;
import com.ruoyi.admin.service.ITLayerSetService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.system.mapper.SysDeptMapper;
import com.ruoyi.common.system.service.ISysDeptService;
import com.ruoyi.common.system.service.ISysUserService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 图层集Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@Service
public class TLayerSetServiceImpl implements ITLayerSetService {
    @Autowired
    private TLayerSetMapper tLayerSetMapper;
    @Autowired
    private TLayerSetLayerMapper layerSetLayerMapper;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private TElementClassificationNormMapper tElementClassificationNormMapper;
    @Autowired
    private TLayerMapper tLayerMapper;
    @Autowired
    private TLayerSetLayerMapper tLayerSetLayerMapper;
    @Autowired
    private ITElementClassificationService tElementClassificationService;
    @Autowired
    private TBaseMapper baseMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询图层集
     *
     * @param id 图层集主键
     * @return 图层集
     */
    @Override
    public TLayerSetVo selectTLayerSetById(Long id) {
        TLayerSet layerSet = tLayerSetMapper.selectTLayerSetById(id);
        if (null == layerSet) return null;
        TLayerSetVo layerSetVo = BeanUtil.toBean(layerSet, TLayerSetVo.class);
        List<TLayer> layerList = tLayerMapper.selectTLayerBySetIdList(layerSetVo.getLayerSetId());
        List<String> codeList = new ArrayList<>();
        for (TLayer layer : layerList) {
            codeList.add(layer.getCode());
        }
        List<TElementClassificationNorm> elementClassificationNormList = tElementClassificationNormMapper.selectTElementClassificationNormByCodeList((String[]) codeList.toArray());
        Set<Long> elmentIdList = new HashSet<>();
        for (TElementClassificationNorm tElementClassificationNorm : elementClassificationNormList) {
            elmentIdList.add(tElementClassificationNorm.getElmentId());
        }
        List<TElementClassification> elementClassificationList = tElementClassificationService.selectTElementClassificationByIds((Long[]) elmentIdList.toArray());
        List<TElementClassificationVo> elementClassificationVoList = new ArrayList<>();
        if (!elementClassificationList.isEmpty()) {
            for (TElementClassification elementClassification : elementClassificationList) {
                TElementClassificationVo elementClassificationVo = BeanUtil.toBean(elementClassification, TElementClassificationVo.class);
                List<TElementClassificationNormVo> elementClassificationNormVoList = new ArrayList<>();
                for (TElementClassificationNorm tElementClassificationNorm : elementClassificationNormList) {
                    if (tElementClassificationNorm.getElmentId().equals(elementClassification.getId())) {
                        TElementClassificationNormVo elementClassificationNormVo = BeanUtil.toBean(tElementClassificationNorm, TElementClassificationNormVo.class);
                        elementClassificationNormVoList.add(elementClassificationNormVo);
                    }
                }
                elementClassificationVo.setElementClassificationNormVoList(elementClassificationNormVoList);
                elementClassificationVoList.add(elementClassificationVo);
            }
        }
        layerSetVo.setElementClassificationVoList(elementClassificationVoList);
        return layerSetVo;
    }

    /**
     * 查询图层集列表
     *
     * @param tLayerSet 图层集
     * @return 图层集
     */
    @Override
    public List<TLayerSet> selectTLayerSetList(TLayerSet tLayerSet) {
        Long baseId = tLayerSet.getBaseId();
        System.out.println("基地id：" + baseId);
        Long[] baseIds;
        if (null == baseId) {
            baseIds = SecurityUtils.getBaseId();
        } else {
            baseIds = new Long[]{baseId};
        }
        if (0 < baseIds.length)
            tLayerSet.setBaseIds(baseIds);
        return tLayerSetMapper.selectTLayerSetList(tLayerSet);
    }

    @Override
    public List<TLayerSetVo> selectTLayerSetVoList(TLayerSet tLayerSet) {
        Long baseId = tLayerSet.getBaseId();
        System.out.println("基地id：" + baseId);
        Long[] baseIds;
        if (null == baseId) {
            baseIds = SecurityUtils.getBaseId();
        } else {
            baseIds = new Long[]{baseId};
        }
        if (0 < baseIds.length)
            tLayerSet.setBaseIds(baseIds);
        List<Long> deptList = buildDeptTreeByDeptId(SecurityUtils.getDeptId());
        deptList.add(SecurityUtils.getDeptId());
        if(!deptList.isEmpty()&&!SecurityUtils.isAdmin())
            tLayerSet.setDeptIds(deptList.toArray(new Long[deptList.size()]));
        List<TLayerSetVo> data = tLayerSetMapper.selectTLayerSetVoList(tLayerSet);
        for (TLayerSetVo layerSetVo : data) {
            if (null != layerSetVo.getDeptId()) {
                String deptIds[] = layerSetVo.getDeptId().split(",");
                String deptName = null;
                for (String deptId : deptIds) {
                    SysDept sysDept = sysDeptMapper.selectDeptById(Long.valueOf(deptId));
                    if (null != sysDept) {
                        if (null == deptName) {
                            deptName = sysDept.getDeptName();
                        } else {
                            deptName += "," + sysDept.getDeptName();
                        }
                    }
                }
                layerSetVo.setDeptName(deptName);
            }
        }
        return data;
    }

    /**
     * 新增图层集
     *
     * @param tLayerSetBo 图层集
     * @return 结果
     */
    @Override
    public AjaxResult insertTLayerSet(TLayerSetBo tLayerSetBo) {
        try {
            tLayerSetBo.setPid(null == tLayerSetBo.getPid() ? 0 : tLayerSetBo.getPid());
            List<TElementClassificationNorm> elementClassificationNormList = new ArrayList<>();
            if (0 < tLayerSetBo.getNorm().length)
                elementClassificationNormList = tElementClassificationNormMapper.selectTElementClassificationNormByIdList(tLayerSetBo.getNorm());
            TLayerSet layerSet = BeanUtil.toBean(tLayerSetBo, TLayerSet.class);
            tLayerSetMapper.insertTLayerSet(layerSet);
            Long baseId = tLayerSetBo.getBaseId();
            System.out.println("基地id：" + baseId);
            Long[] baseIds = new Long[]{baseId};
            if (null == baseIds) baseIds = SecurityUtils.getBaseId();
            for (TElementClassificationNorm tElementClassificationNorm : elementClassificationNormList) {
                TLayer tLayer = new TLayer();
                if (0 < baseIds.length)
                    tLayer.setBaseIds(baseIds);
                tLayer.setCode(tElementClassificationNorm.getCode());
                List<TLayer> layerList = tLayerMapper.selectTLayerList(tLayer);
                for (TLayer layer : layerList) {
                    TLayerSetLayer tLayerSetLayer = new TLayerSetLayer();
                    tLayerSetLayer.setLayerId(layer.getId());
                    tLayerSetLayer.setLayerSetId(layerSet.getId());
                    tLayerSetLayerMapper.insertTLayerSetLayer(tLayerSetLayer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 修改图层集
     *
     * @param tLayerSetBo 图层集
     * @return 结果
     */
    @Override
    public AjaxResult updateTLayerSet(TLayerSetBo tLayerSetBo) {
        try {
            tLayerSetBo.setPid(null == tLayerSetBo.getPid() ? 0 : tLayerSetBo.getPid());
            List<TElementClassificationNorm> elementClassificationNormList = new ArrayList<>();
            if (0 < tLayerSetBo.getNorm().length)
                elementClassificationNormList = tElementClassificationNormMapper.selectTElementClassificationNormByIdList(tLayerSetBo.getNorm());
            TLayerSet layerSet = BeanUtil.toBean(tLayerSetBo, TLayerSet.class);
            tLayerSetMapper.updateTLayerSet(layerSet);
            Long baseId = tLayerSetBo.getBaseId();
            System.out.println("基地id：" + baseId);
            Long[] baseIds = new Long[]{baseId};
            if (null == baseIds) baseIds = SecurityUtils.getBaseId();
            tLayerSetLayerMapper.deleteTLayerSetLayerByLayerSetId(layerSet.getId());
            for (TElementClassificationNorm tElementClassificationNorm : elementClassificationNormList) {
                TLayer tLayer = new TLayer();
                if (0 < baseIds.length)
                    tLayer.setBaseIds(baseIds);
                tLayer.setCode(tElementClassificationNorm.getCode());
                List<TLayer> layerList = tLayerMapper.selectTLayerList(tLayer);
                for (TLayer layer : layerList) {
                    TLayerSetLayer tLayerSetLayer = new TLayerSetLayer();
                    tLayerSetLayer.setLayerId(layer.getId());
                    tLayerSetLayer.setLayerSetId(layerSet.getId());
                    tLayerSetLayerMapper.insertTLayerSetLayer(tLayerSetLayer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除图层集
     *
     * @param ids 需要删除的图层集主键
     * @return 结果
     */
    @Override
    public int deleteTLayerSetByIds(Long[] ids) {
        for (Long id : ids) {
            layerSetLayerMapper.deleteTLayerSetLayerByLayerSetId(id);
        }
        return tLayerSetMapper.deleteTLayerSetByIds(ids);
    }

    /**
     * 删除图层集信息
     *
     * @param id 图层集主键
     * @return 结果
     */
    @Override
    public int deleteTLayerSetById(Long id) {
        layerSetLayerMapper.deleteTLayerSetLayerByLayerSetId(id);
        return tLayerSetMapper.deleteTLayerSetById(id);
    }

    /**
     * 分享图层集
     */
    @Override
    public R<Void> share(LayerSetShareBo bo) {
        try {
            TLayerSet layerSet = tLayerSetMapper.selectTLayerSetById(bo.getLayerSetId());
            if (null == layerSet) return R.fail("图层集不存在");
            List<SysUser> sysUserList = new ArrayList<>();
            if (null != bo.getDeptId() && 0 < bo.getDeptId().length) {
                for (Long deptId : bo.getDeptId()) {
                    SysDept sysDept = sysDeptService.selectDeptById(deptId);
                    if (null == sysDept) return R.fail("部门不存在：" + deptId);
                    SysUser sysUser = new SysUser();
                    sysUser.setDeptId(deptId);
                    sysUserList.addAll(sysUserService.selectUserList(sysUser));
                }
            } else if (null != bo.getUserId() && 0 < bo.getUserId().length) {
                for (Long userId : bo.getUserId()) {
                    SysUser sysUser = sysUserService.selectUserById(userId);
                    if (null == sysUser) return R.fail("用户不存在：" + userId);
                    sysUserList.add(sysUser);
                }
            } else {
                tLayerSetMapper.deleteTLayerSetByLayerSetId(bo.getLayerSetId());
            }
            for (SysUser sysUser : sysUserList) {
                layerSet.setId(null);
                layerSet.setUserId(sysUser.getUserId());
                layerSet.setDeptId(sysUser.getDeptId());
                tLayerSetMapper.deleteTLayerSetByNameAndUserIdAndDeptId(layerSet);
                tLayerSetMapper.insertTLayerSet(layerSet);
            }
            return R.ok();
        } catch (Exception e) {
            return R.fail();
        }
    }

    @Override
    public R<List<ShortestPathVo>> getShortestPath(ShortestPathBo bo) {
        try {
            String start = tLayerSetMapper.ST_AsEWKT(bo.getStartAxisX(), bo.getStartAxisY(), 4326, 3857);
            start = start.replaceAll("SRID=3857;POINT\\(", "").replaceAll("\\)", "");
            String end = tLayerSetMapper.ST_AsEWKT(bo.getEndAxisX(), bo.getEndAxisY(), 4326, 3857);
            end = end.replaceAll("SRID=3857;POINT\\(", "").replaceAll("\\)", "");
            String[] startArr = start.split(" ");
            String[] endArr = end.split(" ");
            bo.setStartAxisX(BigDecimal.valueOf(Double.valueOf(startArr[0])));
            bo.setStartAxisY(BigDecimal.valueOf(Double.valueOf(startArr[1])));
            bo.setEndAxisX(BigDecimal.valueOf(Double.valueOf(endArr[0])));
            bo.setEndAxisY(BigDecimal.valueOf(Double.valueOf(endArr[1])));
            List<ShortestPathVo> data = tLayerSetMapper.getShortestPath(bo);
            for (ShortestPathVo datum : data) {
                String geom = datum.getGeom();
                Map<String, Object> geomMap = JSONUtil.toBean(geom, Map.class);
                List<List<List<Double>>> coordinates = (List<List<List<Double>>>) geomMap.get("coordinates");
                for (List<Double> coordinate : coordinates.get(0)) {
                    BigDecimal x = BigDecimal.valueOf(Double.valueOf(String.valueOf(coordinate.get(0))));
                    BigDecimal y = BigDecimal.valueOf(Double.valueOf(String.valueOf(coordinate.get(1))));
                    String xy = tLayerSetMapper.ST_AsEWKT(x, y, 3857, 4326);
                    xy = xy.replaceAll("SRID=4326;POINT\\(", "").replaceAll("\\)", "");
                    String[] xyArr = xy.split(" ");
                    coordinate.set(0, Double.valueOf(xyArr[0]));
                    coordinate.set(1, Double.valueOf(xyArr[1]));
                }
                geom = JSONUtil.toJsonStr(coordinates.get(0));
                datum.setGeom(geom);
            }
            return R.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail();
    }

    @Override
    public AxisVo axisConvert(AxisBo bo) {
        AxisVo axisVo = new AxisVo();
        String xy = tLayerSetMapper.ST_AsEWKT(bo.getAxisX(), bo.getAxisY(), bo.getOldCoordinate(), bo.getNewCoordinate());
        xy = xy.replaceAll("SRID=" + bo.getNewCoordinate() + ";POINT\\(", "").replaceAll("\\)", "");
        String[] xyArr = xy.split(" ");
        axisVo.setAxisX(BigDecimal.valueOf(Double.valueOf(xyArr[0])));
        axisVo.setAxisY(BigDecimal.valueOf(Double.valueOf(xyArr[1])));
        return axisVo;
    }

    @Override
    public List<TBaseVo> layerTree(Long[] baseIds) {
        List<TBaseVo> data = new ArrayList<>();
        if (null == baseIds) baseIds = SecurityUtils.getBaseId();
        if (baseIds.length > 0) {
            for (Long baseId : baseIds) {
                TBase base = baseMapper.selectTBaseById(baseId);
                if (base != null) {
                    TBaseVo baseVo = BeanUtil.toBean(base, TBaseVo.class);
                    baseVo.setSysDeptVoList(buildDeptTree(baseVo.getId(),SecurityUtils.isAdmin()?null:SecurityUtils.getDeptId()));
                    data.add(baseVo);
                }
            }
        }
        return data;
    }

    /**
     * 递归构建部门树
     *
     * @param deptId 父级ID
     * @return 部门树列表
     */
    private List<Long> buildDeptTreeByDeptId(Long deptId) {
        SysDept sysDept = new SysDept();
        sysDept.setParentId(deptId);
        List<TreeSelect> sysDeptList = sysDeptService.selectDeptTreeListNotBaseId(sysDept);
        return sysDeptList.stream().map(treeSelect -> {
            SysDeptVo sysDeptVo = new SysDeptVo();
            sysDeptVo.setDeptId(treeSelect.getId());
            sysDeptVo.setDeptName(treeSelect.getLabel());
            // 递归处理子部门
            if (!treeSelect.getChildren().isEmpty()) {
                buildDeptTreeByDeptId(treeSelect.getId());
            }
            return sysDeptVo.getDeptId();
        }).collect(Collectors.toList());
    }

    /**
     * 递归构建部门树
     *
     * @param baseId 基础ID
     * @return 部门树列表
     */
    private List<SysDeptVo> buildDeptTree(Long baseId,Long deptId) {
        SysDept sysDept = new SysDept();
        sysDept.setBaseId(baseId);
        sysDept.setParentId(deptId);
        List<TreeSelect> sysDeptList = sysDeptService.selectDeptTreeListByBaseIdOrParentId(sysDept);
        return sysDeptList.stream().map(treeSelect -> {
            SysDeptVo sysDeptVo = new SysDeptVo();
            sysDeptVo.setDeptId(treeSelect.getId());
            sysDeptVo.setDeptName(treeSelect.getLabel());
            // 构建当前部门的图层集合
            List<TLayerSetVo> layerSetVoList = buildLayerSetList(baseId, sysDeptVo.getDeptId());
            sysDeptVo.setLayerSetVoList(layerSetVoList);
            // 递归处理子部门
            if (!treeSelect.getChildren().isEmpty()) {
                sysDeptVo.setChildren(buildDeptTreeRecursive(treeSelect.getChildren(), baseId));
            }
            return sysDeptVo;
        }).collect(Collectors.toList());
    }

    /**
     * 递归构建子部门列表
     *
     * @param children 子部门TreeSelect列表
     * @param baseId   基础ID
     * @return 子部门列表
     */
    private List<SysDeptVo> buildDeptTreeRecursive(List<TreeSelect> children, Long baseId) {
        return children.stream().map(child -> {
            SysDeptVo childVo = new SysDeptVo();
            childVo.setDeptId(child.getId());
            childVo.setDeptName(child.getLabel());
            // 构建子部门的图层集合
            List<TLayerSetVo> layerSetVoList = buildLayerSetList(baseId, childVo.getDeptId());
            childVo.setLayerSetVoList(layerSetVoList);
            // 递归处理子部门的子部门
            if (!child.getChildren().isEmpty()) {
                childVo.setChildren(buildDeptTreeRecursive(child.getChildren(), baseId));
            }
            return childVo;
        }).collect(Collectors.toList());
    }

    /**
     * 构建图层集合
     *
     * @param baseId 基础ID
     * @return 图层集合
     */
    private List<TLayerSetVo> buildLayerSetList(Long baseId, Long deptId) {
        List<TLayerSetVo> layerSetVoList = new ArrayList<>();
        List<TLayerSet> layerSetList = tLayerSetMapper.selectTLayerSetListByDeptId(deptId);

        for (TLayerSet tLayerSet : layerSetList) {
            TLayerSetVo layerSetVo = BeanUtil.toBean(tLayerSet, TLayerSetVo.class);
            List<TLayer> layerList = tLayerMapper.selectTLayerBySetIdList(tLayerSet.getId());
            layerSetVo.setElementClassificationVoList(
                    tElementClassificationService.selectTElementClassificationList(baseId, layerList)
            );
            layerSetVoList.add(layerSetVo);
        }
        return layerSetVoList;
    }

}
