package com.ruoyi.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图层集与图层的关联对象 t_layer_set_layer
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
public class TLayerSetLayer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图层集ID */
    private Long layerSetId;

    /** 图层ID */
    private Long layerId;

    public Long getLayerSetId() {
        return layerSetId;
    }

    public void setLayerSetId(Long layerSetId) {
        this.layerSetId = layerSetId;
    }

    public Long getLayerId() {
        return layerId;
    }

    public void setLayerId(Long layerId) {
        this.layerId = layerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("layerSetId", getLayerSetId())
            .append("layerId", getLayerId())
            .toString();
    }
}
