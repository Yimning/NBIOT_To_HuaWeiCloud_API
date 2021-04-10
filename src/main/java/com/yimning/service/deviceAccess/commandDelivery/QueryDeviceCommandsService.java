package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.QueryDeviceCommands;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface QueryDeviceCommandsService {
    /** 
     * @Description: 查询设备命令
     */
    public QueryDeviceCommands QueryDeviceCommands(QueryDeviceCommands queryDeviceCommands) throws Exception ;

}