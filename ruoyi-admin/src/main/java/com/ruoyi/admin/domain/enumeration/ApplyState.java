package com.ruoyi.admin.domain.enumeration;



public enum ApplyState {
    PENDING("0", "待审核"),
    APPROVED("1", "已通过"),
    REJECTED("2", "未通过");

    private final String value;  // 对应的字典值
    private final String label;   // 对应的显示名称

    ApplyState(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getCode()
    {
        return value;
    }

    public String getInfo()
    {
        return label;
    }
}
