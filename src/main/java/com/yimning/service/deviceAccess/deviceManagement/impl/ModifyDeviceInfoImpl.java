package com.yimning.service.deviceAccess.deviceManagement.impl;


import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.ModifyDeviceInfoService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
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
	 * @Description: 修改设备信息,设备ID不可空
	 */
	@Override
	public HttpResponseResult ModifyDeviceInfo(Device device) throws Exception {
		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();

		// Authentication.get token
		Authentication authentication = new Authentication();
		String accessToken = authentication.accessToken();

		//Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

		//please replace the deviceId, when you call this interface.
		//String deviceId = "3abc6184-9969-4bb6-95d9-f8646a3511ad";
		String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + device.getDeviceId();

		//please replace the following parameter values, when you call this interface.

		//以类的方式作为参数直接修改，但deviceId不为空，也可以用其他方式。
		String jsonRequest = JsonUtil.jsonObj2Sting(device);

		Map<String, String> header = new HashMap<>();
		header.put(Constant.HEADER_APP_KEY, appId);
		header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

		StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
				header, jsonRequest);

		System.out.println("ModifyDeviceInfo, response content:");
		System.out.println(responseModifyDeviceInfo.getStatusLine());
		System.out.println(responseModifyDeviceInfo.getContent());
		System.out.println();

		HttpResponseResult responseResult = new HttpResponseResult();
		if (responseModifyDeviceInfo.getContent()!=null){
			responseResult	= JSONObject.parseObject(responseModifyDeviceInfo.getContent(), HttpResponseResult.class);
		}
		responseResult.setStatus_code(responseModifyDeviceInfo.getStatusLine().getStatusCode());
		responseResult.setReason_phrase(responseModifyDeviceInfo.getStatusLine().getReasonPhrase());
		System.out.println(responseResult);
		return responseResult;
	}
}