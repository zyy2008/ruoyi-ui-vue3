package com.ruoyi.admin.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地块历史用途信息对象 t_land_use_history
 *
 * @author ruoyi
 * @date 2025-06-11
 */
@Data
@ApiModel("地块历史用途信息")
public class TLandUseHistory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 起始时间
     */
    @ApiModelProperty(name = "起始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(name = "结束时间")
    private String endTime;

    /**
     * 土地用途
     */
    @ApiModelProperty(name = "土地用途")
    private String landUse;

}
