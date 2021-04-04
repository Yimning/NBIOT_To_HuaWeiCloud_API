package com.yimning.entity;

import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description:设备信息
 * @author: Yimning
 * @create: 2021-04-02 22:02
 **/

@Data
public class Device {

    private String app_key; //访问物联网平台的应用ID，在物联网平台创建应用时由平台分配获得。
    private String Authorization;       //访问物联网平台的认证信息，值为“Bearer {accessToken}”，其中{accessToken}为调用鉴权接口返回的accessToken
    private String appId;            //设备所属的应用ID，当修改授权应用下的设备信息时才需要填写。
    private String deviceId;        //设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
    private String deviceType;      //设备类型
    private String location;        //位置
    private String productId;       //产品ID
    private String manufacturerId;      //厂商ID
    private String manufacturerName;    //厂商名字
    private String model;            //设备型号，由厂商定义
    private String mute;           //表示设备是否处于冻结状态，即设备上报数据时，平台是否会管理和保存 TRUE：冻结状态 FALSE：非冻结状态
    private String name;         //设备名称
    private String timezone;        //设备所在时区信息，使用时区编码
    private String region;           //设备区域信息
    private String organization;       //设备所属的组织信息
    private String protocolType;       //设备使用的协议类型，当前支持的协议类型：CoAP，LWM2M，MQTT
    private boolean mqttConnect;        //设备是否使用MQTT协议接入，注册MQTT协议接入设备时需要设置为true
    private String secret;           //设备密码，格式要求为20位16进制数。若在请求中指定secret，则响应中返回请求中指定的secret；若请求中不指定secret，则由物联网平台自动生成
    private DeviceInfo deviceInfo;          //添加设备时封装



    /** 
    * 验证码方式时可用以下变量
    */
    private String imsi;  //NB-IoT终端的IMSI
    private String ip;    //设备的IP地址
    private boolean isSecure;  //指定设备是否为安全设备，默认值为false。在NB-IoT场景下，注册的设备为加密设备时，isSecure要设置为true。
    private String psk;     //psk参数
    private Integer timeout;      //设备验证码的超时时间，单位：秒
    private String verifyCode;     //设备验证码，全局唯一，建议与nodeId设置成相同值。若在请求中指定verifyCode，则响应中返回请求中指定的verifyCode；若请求中不指定verifyCode，则由物联网平台自动生成
    private String deviceName;      //设备名称


}
