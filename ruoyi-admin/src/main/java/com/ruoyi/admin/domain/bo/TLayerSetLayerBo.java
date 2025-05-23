package com.ruoyi.admin.domain.bo;

import java.io.Serializable;
import java.util.List;

public class TLayerSetLayerBo implements Serializable {
    /** 图层集ID */
    private Long layerSetId;

    private List<Long> layerId;


    public Long getLayerSetId() {
        return layerSetId;
    }

    public void setLayerSetId(Long layerSetId) {
        this.layerSetId = layerSetId;
    }

    public List<Long> getLayerId() {
        return layerId;
    }

    public void setLayerId(List<Long> layerId) {
        this.layerId = layerId;
    }
}
