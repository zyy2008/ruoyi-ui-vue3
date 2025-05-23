package com.ruoyi.admin.domain.enumeration;



public enum DelFlag {
    NOTREMOVE(0, "未删除"),
    REMOVE(1, "已删除");

    private final Integer value;  // 对应的字典值
    private final String label;   // 对应的显示名称

    DelFlag(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getCode()
    {
        return value;
    }

    public String getInfo()
    {
        return label;
    }
}
