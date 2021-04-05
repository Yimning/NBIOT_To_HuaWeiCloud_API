package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceHistoryData;
import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface QueryDeviceHistoryDataService {
    /** 
     * @Description: 查设备服务能力
     */
    public DeviceHistoryData QueryDeviceHistoryData(DeviceHistoryData deviceHistoryData) throws Exception ;

}
