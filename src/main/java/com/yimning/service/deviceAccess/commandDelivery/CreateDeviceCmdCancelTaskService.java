package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceCommands;
import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;


public interface CreateDeviceCmdCancelTaskService {
    /** 
     * @Description: 创建设备命令撤销任务
     */
    public DeviceCommands CreateDeviceCmdCancelTask(DeviceCommands deviceCommands) throws Exception ;

}
