package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.common.lang.HttpResponseResult;


public interface DeleteDeviceService {
	/** 
	 * @Description: 删除设备
	 */
	public HttpResponseResult DeleteDevice(String deviceId) throws Exception ;

}