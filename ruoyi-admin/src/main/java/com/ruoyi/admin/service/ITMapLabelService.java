package com.ruoyi.admin.service;

import com.ruoyi.admin.domain.TMapLabel;
import com.ruoyi.admin.domain.bo.MapLabelShareBo;
import com.ruoyi.common.core.domain.R;

import java.util.List;

/**
 * 地图标注Service接口
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
public interface ITMapLabelService 
{
    /**
     * 查询地图标注
     * 
     * @param id 地图标注主键
     * @return 地图标注
     */
    public TMapLabel selectTMapLabelById(Long id);

    /**
     * 查询地图标注列表
     * 
     * @param tMapLabel 地图标注
     * @return 地图标注集合
     */
    public List<TMapLabel> selectTMapLabelList(TMapLabel tMapLabel);

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
     * 批量删除地图标注
     * 
     * @param ids 需要删除的地图标注主键集合
     * @return 结果
     */
    public int deleteTMapLabelByIds(Long[] ids);

    /**
     * 删除地图标注信息
     * 
     * @param id 地图标注主键
     * @return 结果
     */
    public int deleteTMapLabelById(Long id);

    /**
     * 分享地图标注数据
     */
    public R<Void> share(MapLabelShareBo bo);
}
