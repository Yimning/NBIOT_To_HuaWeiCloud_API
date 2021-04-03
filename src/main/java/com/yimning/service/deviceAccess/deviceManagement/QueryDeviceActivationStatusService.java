package com.yimning.service.deviceAccess.deviceManagement;

import java.util.HashMap;
import java.util.Map;

import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.QueryDeviceActivationStatus;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface QueryDeviceActivationStatusService {
    /** 
     * @Description: 查询设备激活状态
     */
    public QueryDeviceActivationStatus QueryDeviceActivationStatus(String deviceId) throws Exception ;

}
