package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceCommands;
import com.yimning.entity.QueryDeviceCommands;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;


public interface ModifyDeviceCommandService {
    /** 
     * @Description: 修改设备命令
     */
    public DeviceCommands ModifyDeviceCommand(DeviceCommands deviceCommands) throws Exception ;

}
