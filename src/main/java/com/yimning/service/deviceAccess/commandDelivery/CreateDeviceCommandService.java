package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceCommands;
import com.yimning.entity.QueryDeviceCapabilities;
import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;


public interface CreateDeviceCommandService {
    /** 
     * @Description: 创建设备的命令
     */
    public DeviceCommands CreateDeviceCommand(DeviceCommands deviceCommands) throws Exception ;

}
