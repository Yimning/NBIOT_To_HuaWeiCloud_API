package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.common.lang.HttpResponseResult;

public interface DeleteSubDeviceService {
    /** 
     * @Description: 删除子设备
     */
    public HttpResponseResult DeleteSubDeviceService(String deviceId) throws Exception;

}