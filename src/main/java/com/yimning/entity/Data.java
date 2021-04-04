package com.yimning.entity;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 设备的采集数据类，此类由用户对设备采集数据字段自定义
 * @author: Yimning
 * @create: 2021-04-04 14:10
 **/
@lombok.Data
public class Data {
    private int temperatureValue;
    private int humidValue;
    private int smog;
    private String relayState;
    private String beepState;
}
