package com.yimning.entity;

import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 查询设备影子返回结果
 * @author: Yimning
 * @create: 2021-04-04 00:48
 **/
@Data
public class QueryDeviceShadow {

    private String deviceId;
    private String gatewayId;
    private String nodeType;
    private String createTime;
    private String lastModifiedTime;
    private DeviceInfo deviceInfo;
    private String services;
    private int status_code;
    private String reason_phrase;
}
