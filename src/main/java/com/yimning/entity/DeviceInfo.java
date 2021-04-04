package com.yimning.entity;

import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 设备的属性
 * @author: Yimning
 * @create: 2021-04-04 14:17
 **/
@Data
public class DeviceInfo {
    private String nodeId;
    private String name;
    private String description;
    private String manufacturerId;
    private String manufacturerName;
    private String mac;
    private String location;
    private String deviceType;
    private String model;
    private String swVersion;
    private String fwVersion;
    private String hwVersion;
    private String protocolType;
    private String bridgeId;
    private String status;
    private String statusDetail;
    private String mute;
    private String supportedSecurity;
    private String isSecurity;
    private String signalStrength;
    private String sigVersion;
    private String serialNumber;
    private String batteryLevel;
    private String isHD;

}
