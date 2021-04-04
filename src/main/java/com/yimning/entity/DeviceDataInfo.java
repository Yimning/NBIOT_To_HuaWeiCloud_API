package com.yimning.entity;

import com.yimning.common.lang.HttpResponseResult;

import java.util.List;
/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 设备的信息和数据情况
 * @author: Yimning
 * @create: 2021-04-04 14:04
 **/
@lombok.Data
public class DeviceDataInfo {
    private String deviceId;
    private String gatewayId;
    private String nodeType;
    private String createTime;
    private String lastModifiedTime;
    private DeviceInfo deviceInfo;
    private List<Services> services;
    private HttpResponseResult httpResponseResult;


    @lombok.Data
    public class Services {
        private String serviceId;
        private String serviceType;
        private Data data;   //由用户自定义
        private String eventTime;
        private String serviceInfo;
    }
}