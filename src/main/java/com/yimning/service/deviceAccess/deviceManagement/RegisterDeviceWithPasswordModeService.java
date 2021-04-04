package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.entity.Device;
import com.yimning.common.lang.HttpResponseResult;

public interface RegisterDeviceWithPasswordModeService {
    public HttpResponseResult RegisterDeviceWithPasswordMode(Device device) throws Exception ;
}
