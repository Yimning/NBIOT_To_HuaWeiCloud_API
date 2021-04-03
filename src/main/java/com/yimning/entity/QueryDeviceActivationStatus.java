package com.yimning.entity;

import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 查询激活状态返回结果
 * @author: Yimning
 * @create: 2021-04-04 00:48
 **/
@Data
public class QueryDeviceActivationStatus {
    private String deviceId;
    private String activated;
    private String name;
    private int status_code;
    private String reason_phrase;
}
