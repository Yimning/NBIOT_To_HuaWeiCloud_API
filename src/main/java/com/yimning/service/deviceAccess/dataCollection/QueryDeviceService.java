package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.Device;
import com.yimning.entity.DeviceDataInfo;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface QueryDeviceService {
    /** 
     * @Description: 查设备数据
     */
    public DeviceDataInfo QueryDevice(String deviceId) throws Exception ;

}
