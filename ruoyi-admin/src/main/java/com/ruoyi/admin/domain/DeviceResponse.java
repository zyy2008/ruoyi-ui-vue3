package com.ruoyi.admin.domain;

import lombok.Data;
import java.util.List;

/**
 * 在线监测同步的数据结构
 */
@Data
public class DeviceResponse {
    private int status;
    private DataInner data;

    @Data
    public static class DataInner {
        private String deviceName;
        private String deviceNo;
        private String lng;
        private String lat;
        private String elevation;
        private String connDate;
        private String status;
        private int reportCycle;
        private List<Sensor> sensors;
    }

    @Data
    public static class Sensor {
        private int index;
        private String code;
        private String name;
        private String value;
        private String unit;
        private String time;
    }
}
