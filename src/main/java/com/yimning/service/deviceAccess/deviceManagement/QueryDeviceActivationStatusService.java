package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.entity.QueryDeviceActivationStatus;

public interface QueryDeviceActivationStatusService {
    /** 
     * @Description: 查询设备激活状态
     */
    public QueryDeviceActivationStatus QueryDeviceActivationStatus(String deviceId) throws Exception ;

}
