package com.ruoyi.admin.mapper;

import com.ruoyi.admin.domain.TMapLabel;
import com.ruoyi.common.annotation.DataScope;

import java.util.List;

/**
 * 地图标注Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
public interface TMapLabelMapper 
{
    /**
     * 查询地图标注
     * 
     * @param id 地图标注主键
     * @return 地图标注
     */
    public TMapLabel selectTMapLabelById(Long id);

    /**
     * 获取基地id
     */
    List<Long> selectBaseIdByUserId(Long userId);

    /**
     * 查询地图标注列表
     * 
     * @param tMapLabel 地图标注
     * @return 地图标注集合
     */
    @DataScope(deptAlias = "ml", userAlias = "ml")
    public List<TMapLabel> selectTMapLabelList(TMapLabel tMapLabel);
    public List<TMapLabel> selectTMapLabelListByMapLabelId(Long id);

    /**
     * 新增地图标注
     * 
     * @param tMapLabel 地图标注
     * @return 结果
     */
    public int insertTMapLabel(TMapLabel tMapLabel);

    /**
     * 修改地图标注
     * 
     * @param tMapLabel 地图标注
     * @return 结果
     */
    public int updateTMapLabel(TMapLabel tMapLabel);

    /**
     * 删除地图标注
     * 
     * @param id 地图标注主键
     * @return 结果
     */
    public int deleteTMapLabelById(Long id);

    /**
     * 批量删除地图标注
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTMapLabelByIds(Long[] ids);
    public int deleteTMapLabelByNameAndUserIdAndDeptId(TMapLabel mapLabel);

    /**
     * 根据地图标注id删除分享的数据
     * @param mapLabelId
     * @return
     */
    public int deleteTLayerSetByMapLabelId(Long mapLabelId);
}
