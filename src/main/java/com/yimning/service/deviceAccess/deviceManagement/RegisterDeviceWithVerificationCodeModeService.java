package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

public interface RegisterDeviceWithVerificationCodeModeService {
    public HttpResponseResult RegisterDeviceWithVerificationCodeMode(Device device) throws Exception ;
}
