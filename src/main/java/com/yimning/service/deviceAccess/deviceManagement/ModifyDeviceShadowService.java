package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.common.lang.HttpResponseResult;

public interface ModifyDeviceShadowService {
    /** 
     * @Description: 修改设备影子
     */
    public HttpResponseResult ModifyDeviceShadow(String deviceId) throws Exception ;

}