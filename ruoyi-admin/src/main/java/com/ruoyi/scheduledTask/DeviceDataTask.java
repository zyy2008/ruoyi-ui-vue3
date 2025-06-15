package com.ruoyi.scheduledTask;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.admin.domain.DeviceResponse;
import com.ruoyi.admin.domain.TMonitoringWells;
import com.ruoyi.admin.domain.TRealtimeWaterQualityMonitoring;
import com.ruoyi.admin.service.ITMonitoringWellsService;
import com.ruoyi.admin.service.ITRealtimeWaterQualityMonitoringService;
import com.ruoyi.common.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class DeviceDataTask {

    private static final String BASE_URL = "http://admin.yun-sense.com/v1/getDeviceData";
    private static final String API_KEY = "YpMDbau7rxFI7QIkQsQiDLBSnr65OiCU";

    @Autowired
    private ITMonitoringWellsService tMonitoringWellsService;
    @Autowired
    private ITRealtimeWaterQualityMonitoringService itRealtimeWaterQualityMonitoringService;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void fetchDeviceData() {
        log.info("=================同步监测进数据");
        try {
            TMonitoringWells tMonitoringWells = new TMonitoringWells();
            tMonitoringWells.setRealTime("是");
            List<TMonitoringWells> list = tMonitoringWellsService.selectTMonitoringWellsList(tMonitoringWells);
            int wellRow = 0;
            for (TMonitoringWells monitoringWells : list) {
                if (null != monitoringWells.getDeviceNo()) {
                    String data = syncDeviceData(monitoringWells.getDeviceNo());
                    if (null != data && (-1 == data.indexOf("500") || -1 == data.indexOf("errorCode"))) {
                        DeviceResponse response = JSONUtil.toBean(data, DeviceResponse.class);
                        if (response.getStatus() == 200) {
                            if (response.getData().getDeviceNo().equals(monitoringWells.getDeviceNo())
                                    && response.getData().getDeviceName().equals(monitoringWells.getWellCode())) {
                                monitoringWells.setLongitude(response.getData().getLng());
                                monitoringWells.setLatitude(response.getData().getLat());
                                monitoringWells.setWellElevation(response.getData().getElevation());
                                tMonitoringWellsService.updateTMonitoringWells(monitoringWells);

                                TRealtimeWaterQualityMonitoring tRealtimeWaterQualityMonitoring = new TRealtimeWaterQualityMonitoring();
                                tRealtimeWaterQualityMonitoring.setMonitoringTime(response.getData().getConnDate());
                                tRealtimeWaterQualityMonitoring.setMonitoringWell(monitoringWells.getWellCode());
                                List<TRealtimeWaterQualityMonitoring> tRealtimeWaterQualityMonitoringList = itRealtimeWaterQualityMonitoringService.selectTRealtimeWaterQualityMonitoringList(tRealtimeWaterQualityMonitoring);
                                if (tRealtimeWaterQualityMonitoringList.isEmpty()) {
                                    for (DeviceResponse.Sensor sensor : response.getData().getSensors()) {
                                        tRealtimeWaterQualityMonitoring.setId(IdUtils.randomUUID());
                                        tRealtimeWaterQualityMonitoring.setSynchronizationTime(DateUtil.formatTime(new Date()));
                                        if ("0".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setTemperature(sensor.getValue());
                                        } else if ("1".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setDissolvedOxygen(sensor.getValue());
                                        } else if ("2".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setPhValue(sensor.getValue());
                                        } else if ("3".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setConductivity(sensor.getValue());
                                        } else if ("5".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setAmmoniaNitrogen(sensor.getValue());
                                        } else if ("7".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setRedoxPotential(sensor.getValue());
                                        } else if ("238".equals(sensor.getCode())) {
                                            tRealtimeWaterQualityMonitoring.setWaterLevel(sensor.getValue());
                                        }
                                    }
                                    itRealtimeWaterQualityMonitoringService.insertTRealtimeWaterQualityMonitoring(tRealtimeWaterQualityMonitoring);
                                    wellRow++;
                                } else {
                                    log.info("该数据已存在，不更新：{}-{} : {}", monitoringWells.getDeviceNo(), monitoringWells.getWellCode(), response.getData().getConnDate());
                                }

                            }
                        }
                    } else {
                        log.info("获取异常的监测井：{}", monitoringWells.getDeviceNo());
                    }
                }
            }
            log.info("需要同步的监测井数量：{}", list.size());
            log.info("实际完成同步的监测井数量：{}", wellRow);
            log.info("=================同步监测进数据完成");
        } catch (Exception e) {
            log.error("=================同步监测进数据失败: " + e.getMessage());
        }
    }

    public String syncDeviceData(String deviceNo) {
        long timestamp = System.currentTimeMillis();
        // 构造参数并按 ASCII 升序排序
        Map<String, String> params = new TreeMap<>(MapUtil.sort(MapUtil.of("deviceNo", deviceNo)));
        // 拼接参数名和值
        StringBuilder paramBuilder = new StringBuilder();
        params.forEach((k, v) -> paramBuilder.append(k).append(v));
        // 拼接待签名字符串
        String toSign = timestamp + paramBuilder.toString() + API_KEY;
        // 生成 MD5 大写签名
        String sign = DigestUtil.md5Hex(toSign).toUpperCase();
        // 构造 URL 参数字符串
        String query = StrUtil.format("deviceNo={}&timestamp={}&sign={}", deviceNo, timestamp, sign);
        String requestUrl = BASE_URL + "?" + query;
        System.out.println("[" + DateUtil.now() + "] 请求地址: " + requestUrl);
        // 发 GET 请求
        String response = HttpUtil.get(requestUrl);
        System.out.println("返回结果: " + response.replaceAll(" ", ""));
        return response;
    }
}
