package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceDataInfo;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface QueryDevicesService {
    /** 
     * @Description: 查所有设备数据
     */
    public DeviceDataInfo QueryDevices(String deviceId) throws Exception ;

}

