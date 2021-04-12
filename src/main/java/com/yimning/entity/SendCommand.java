package com.yimning.entity;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.common.lang.HttpResponseResult;
import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 设备命令下发
 * @author: Yimning
 * @create: 2021-04-12 15:27
 **/
@Data
public class SendCommand {

    private String app_key;//访问物联网平台的应用ID，在物联网平台创建应用时由平台分配获得。
    private String Authorization;//访问物联网平台的认证信息，值为“Bearer {accessToken}”，其中{accessToken}为调用鉴权接口返回的accessToken。
    private String deviceId;//设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
    private String serviceId;//命令对应的服务ID，用于标识一个服务。要与profile中定义的serviceId保持一致。
    private String appId;//命令所属的应用ID，当创建授权应用下的命令时才需要填写。
    private CommandNA2CloudHeader header;//消息的消息头。
    private ObjectNode body;//消息的消息体，jsonString格式，里面是一个个健值对（key: value），“key”是产品模型中命令参数的参数名（paraName），“value”是该命令参数要设置的值，根据产品模型中命令参数的取值范围自定义设置。
    private String requestId;
    private String status;
    private String timestamp;
    private HttpResponseResult httpResponseResult;


    @Data
    public static class CommandNA2CloudHeader {
        private String requestId;//用于标识一个命令，不能重复。
        public String mode;
        //是否要确认消息，默认为ACK模式。NOACK：不需要确认消息;ACK：需要确认消息
        private String from;//表示消息发布者的地址。
        //    App发起的请求：/users/{userId}
        //    应用发起的请求：/{serviceName}
        //    物联网平台发起的请求：/cloud/{serviceName}

        private String toType;//消息接收者的类型，默认为GATEWAY。
        private String to;//消息接收者的地址。
        private String method;//命令名称，要与profile中定义的命令名保持一致。
        private String callbackURL;
    }
}
