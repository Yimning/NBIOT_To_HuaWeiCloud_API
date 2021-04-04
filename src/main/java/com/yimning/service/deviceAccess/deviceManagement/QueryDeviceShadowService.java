package com.yimning.service.deviceAccess.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.QueryDeviceActivationStatus;
import com.yimning.entity.QueryDeviceShadow;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface QueryDeviceShadowService {
    /** 
     * @Description: 查询设备影子激活状态
     */
    public QueryDeviceShadow QueryDeviceShadow(String deviceId) throws Exception ;

}
