package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Device Commands :
 * 
 * After an NA delivers a command to a device, the NA can call this API to query 
 * the status and content of the delivered command on the IoT platform to check 
 * the command execution status.
 */
public class QueryDeviceCommandsImpl {


	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;
        String urlQueryDeviceCMD = Constant.QUERY_DEVICE_CMD;
        
        //please replace the pageSize and startTime, when you call this interface.
        Integer pageSize = 100;
        String startTime = "20190101T121212Z";
        
        Map<String, String> paramQueryDeviceCommands = new HashMap<>();
        paramQueryDeviceCommands.put("pageSize", pageSize.toString());
        paramQueryDeviceCommands.put("startTime", startTime);
        
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryDeviceCMD = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceCMD, paramQueryDeviceCommands, header);
        
        System.out.println("QueryDeviceCommands, response content:");
		System.out.println(responseQueryDeviceCMD.getStatusLine());
		System.out.println(responseQueryDeviceCMD.getContent());
        System.out.println();
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