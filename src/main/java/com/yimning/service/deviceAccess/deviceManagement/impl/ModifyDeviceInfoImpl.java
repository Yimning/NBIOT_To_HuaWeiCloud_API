package com.yimning.service.deviceAccess.deviceManagement.impl;


import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.ModifyDeviceInfoService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Deleting a Device :
 * 
 * If a device that has been registered on the IoT platform does not need to connect to 
 * the IoT platform, an NA can call this API to delete the device. If the device needs 
 * to connect to the IoT platform again, register it again.
 *
 */
@Service
public class ModifyDeviceInfoImpl implements ModifyDeviceInfoService {
	/** 
	 * @Description: 修改设备信息
	 */
	@Override
	public HttpResponseResult ModifyDeviceInfo(Device device) throws Exception {
		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();

		Authentication authentication = new Authentication();
		// Authentication.get token
		String accessToken = authentication.accessToken();

		//Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

		//please replace the deviceId, when you call this interface.
		//String deviceId = "28041385-2a0c-4d47-9964-6af69d52a58a";
		String urlDeleteDevice = Constant.DELETE_DEVICE + "/" +deviceId;

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

		StreamClosedHttpResponse responseDeleteDevice = httpsUtil.doDeleteWithParasGetStatusLine(urlDeleteDevice, null, header);

		System.out.println("DeleteDevice, response content:");

		HttpResponseResult responseResult = JSONObject.parseObject(responseDeleteDevice.getContent(), HttpResponseResult.class);
		responseResult.setStatus_code(responseDeleteDevice.getStatusLine().getStatusCode());
		responseResult.setReason_phrase(responseDeleteDevice.getStatusLine().getReasonPhrase());
		//System.out.println(responseResult);
		return responseResult;
	}
}