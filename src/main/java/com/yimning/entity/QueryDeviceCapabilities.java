package com.yimning.entity;

import com.yimning.common.lang.HttpResponseResult;
import lombok.Data;

import java.util.List;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 查询设备的服务能力
 * @author: Yimning
 * @create: 2021-04-04 20:18
 **/
@Data
public class QueryDeviceCapabilities {
    private String app_key;
    private String Authorization;
    private String gatewayId;
    private String appId;
    private String deviceId;
    private HttpResponseResult httpResponseResult;
    private List<DeviceCapabilities> deviceCapabilities;

    @Data
    public class DeviceCapabilities {
        private String deviceId;
        private List<ServiceCapabilities> serviceCapabilities;
    }

    @Data
    public class ServiceCapabilities {
        private String serviceId;
        private String serviceType;
        private String option;
        private String description;
        private List<Commands> commands;
        private List<Properties> properties;
    }

    @Data
    public class Commands {
        private String commandName;
        private List<Paras> paras;
        private List<Responses> responses;
    }

    @Data
    public class Responses {
        private String responseName;
        private List<Paras> paras;
    }

    @Data
    public class Paras {
        private String paraName;
        private String dataType;
        private boolean required;
        private String min;
        private String max;
        private int step;
        private int maxLength;
        private String unit;
        private String enumList;
    }

    @Data
    public class Properties {
        private String propertyName;
        private String dataType;
        private boolean required;
        private String min;
        private String max;
        private int step;
        private int maxLength;
        private String method;
        private String unit;
        private String enumList;
    }

}
