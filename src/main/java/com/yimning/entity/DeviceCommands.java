package com.yimning.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.common.lang.HttpResponseResult;
import lombok.Data;

import java.util.List;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 设备命令
 * @author: Yimning
 * @create: 2021-04-05 21:05
 **/
@Data
public class DeviceCommands {
    private String app_key;
    private String Authorization;
    private String commandId;
    private String appId;
    private String deviceId;  //命令所属的应用ID，当创建授权应用下的命令时才需要填写。
    private Command command;
    private String callbackUrl;
    /*
    下发命令的有效时间，单位为秒，表示设备命令在创建后expireTime秒内有效，超过这个时间范围后命令将不再下发，如果未设置则默认为48小时（86400s*2）。
    如果expireTime设置为0，则无论物联网平台上设置的命令下发模式是什么，该命令都会立即下发给设备（如果设备处于休眠状态或者链路已老化，则设备收不到命令，平台没收到设备的响应，该命令最终会超时）。
    如果expireTime不为0，则在expireTime时间内命令缓存在平台（PENDING状态），仅当设备上线或向平台上报数据时，命令会下发给设备。
*/
    private int expireTime;
    private String status;
    private String creationTime;
    private String platformIssuedTime;
    private int issuedTimes;
    private int maxRetransmit;//命令下发最大重传次数。
    private HttpResponseResult httpResponseResult;

//    @Data
//    public class Paras {
//        private String value;
//    }

    @lombok.Data
    public  class Command {
        //命令对应的服务ID，用于标识一个服务。要与profile中定义的serviceId保持一致。
        private String serviceId;
        //命令服务下具体的命令名称，要与profile中定义的命令名保持一致。
        private String method;

        /*命令参数，jsonString格式，里面是一个个健值对（key: value），
           “key”是产品模型中命令参数的参数名（paraName），
            “value”是该命令参数要设置的值，根据产品模型中命令参数的取值范围自定义设置。
         */
        private ObjectNode paras;


//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode parentNode = objectMapper.createObjectNode();

    }

/*    public DeviceCommands(String json) throws JsonProcessingException {
        // 实例化 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 json 转成 JsonNode 对象
        JsonNode rootNode = objectMapper.readTree(json.toString());
        System.out.println(rootNode);

        // 创建新节点
        com.fasterxml.jackson.databind.node.ObjectNode newNode = objectMapper.createObjectNode();
        newNode.setAll((com.fasterxml.jackson.databind.node.ObjectNode)rootNode);
        System.out.println(newNode);
        Command command =  new Command();
        command.paras = newNode;
        this.command = command;
    }*/

    //撤销命令时可用
    private String taskId;
    private int totalCount;
    private List<DeviceCommandResp> deviceCommands;
//字符串？
    @Data
    public static class DeviceCommandResp {
        private String commandId;
        private String appId;
        private String deviceId;
        private Command command;
        private String callbackUrl;
        private int expireTime;
        private String status;
        private ObjectNode result;
        private String creationTime;
        private String executeTime;
        private String platformIssuedTime;
        private String deliveredTime;
        private int issuedTimes;
        private int maxRetransmit;
    }


}