package com.ruoyi.common.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户基地对象 sys_user_base
 *
 * @author ruoyi
 * @date 2024-11-12
 */
public class SysUserBase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 基地id
     */
    private Long baseId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Long getBaseId() {
        return baseId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("baseId", getBaseId())
                .toString();
    }
}
