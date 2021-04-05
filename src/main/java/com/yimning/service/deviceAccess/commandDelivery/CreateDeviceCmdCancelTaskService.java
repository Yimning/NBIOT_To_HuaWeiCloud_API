package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;


public interface CreateDeviceCmdCancelTaskService {
    /** 
     * @Description: 查设备服务能力
     */
    public QueryDeviceCapabilities QueryDeviceCapabilities(QueryDeviceCapabilities queryDeviceCapabilities) throws Exception ;

}
