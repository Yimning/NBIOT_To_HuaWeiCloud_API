package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.entity.Device;
import com.yimning.common.lang.HttpResponseResult;

public interface RegisterDeviceWithVerificationCodeModeService {
    public HttpResponseResult RegisterDeviceWithVerificationCodeMode(Device device) throws Exception ;
}
