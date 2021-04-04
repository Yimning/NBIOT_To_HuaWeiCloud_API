package com.yimning.service.deviceAccess.deviceManagement.impl;


import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.DeleteDeviceService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

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
public class DeleteDeviceImpl implements DeleteDeviceService {
	/** 
	 * @Description: 删除调试设备
	 */
	public HttpResponseResult DeleteDevice(String deviceId) throws Exception {

		// Two-Way Authentication
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();

		// Authentication.get token
		Authentication authentication = new Authentication();
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
/*
		System.out.println("----"+responseDeleteDevice.getStatusLine());
		System.out.println("===="+responseDeleteDevice.getContent());
*/

		System.out.println(""+responseDeleteDevice);
		HttpResponseResult responseResult = new HttpResponseResult();
		if(responseDeleteDevice.getContent()!=null){
			responseResult = JSONObject.parseObject(responseDeleteDevice.getContent(), HttpResponseResult.class);
		}
		responseResult.setStatus_code(responseDeleteDevice.getStatusLine().getStatusCode());
		responseResult.setReason_phrase(responseDeleteDevice.getStatusLine().getReasonPhrase());
		//System.out.println(responseResult);
		return responseResult;
	}


	/**
	 * Authentication.get token
	 */
	@SuppressWarnings("unchecked")
	public static String login(HttpsUtil httpsUtil) throws Exception {

		String appId = Constant.APPID;
		String secret = Constant.SECRET;
		String urlLogin = Constant.APP_AUTH;

		Map<String, String> paramLogin = new HashMap<>();
		paramLogin.put("appId", appId);
		paramLogin.put("secret", secret);

		StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

		System.out.println("app auth success,return accessToken:");
		System.out.println(responseLogin.getStatusLine());
		System.out.println(responseLogin.getContent());
		System.out.println();

		Map<String, String> data = new HashMap<>();
		data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
		return data.get("accessToken");
	}

}