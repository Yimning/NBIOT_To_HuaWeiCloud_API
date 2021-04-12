package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.entity.DeviceCommands;
import com.yimning.entity.SendCommand;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface InvokeDeviceServicesService {
    /** 
     * @Description: 下发设备命令服务,仅适用于使用MQTT协议接入的设备
     */
    public SendCommand InvokeDeviceServices(SendCommand sendCommand) throws Exception ;

}
