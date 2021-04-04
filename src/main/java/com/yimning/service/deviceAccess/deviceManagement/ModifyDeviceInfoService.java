package com.yimning.service.deviceAccess.deviceManagement;

import com.yimning.entity.Device;
import com.yimning.common.lang.HttpResponseResult;


public interface ModifyDeviceInfoService {
	/** 
	 * @Description: 修改设备信息
	 */
	public HttpResponseResult ModifyDeviceInfo(Device device) throws Exception ;

}