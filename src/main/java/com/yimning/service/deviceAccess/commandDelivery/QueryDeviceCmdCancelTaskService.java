package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceCommands;
import com.yimning.entity.QueryDeviceCommands;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;


public interface QueryDeviceCmdCancelTaskService {
    /** 
     * @Description: 查询设备命令撤销任务
     */
    public DeviceCommands QueryDeviceCmdCancelTask(QueryDeviceCommands queryDeviceCommands) throws Exception ;

}

