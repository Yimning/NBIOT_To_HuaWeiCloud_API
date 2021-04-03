package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Service Capabilities of a Device :
 * 
 * If an NA needs to know which service attributes can be reported by a device and 
 * which commands can be delivered to the device, the NA can call this API to query 
 * the device service capabilities defined in the profile file of the device on the 
 * IoT platform.
 */
public class QueryDeviceCapabilitiesController {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceCapabilities = Constant.QUERY_DEVICE_CAPABILITIES;

        //please replace the deviceId and gatewayId, when you call this interface.
        String deviceId = "de71ad4a-211e-4e53-b48c-80cb2d5c88c8";
        String gatewayId = "de71ad4a-211e-4e53-b48c-80cb2d5c88c8";

        Map<String, String> paramQueryDeviceCapabilities = new HashMap<>();
        paramQueryDeviceCapabilities.put("deviceId", deviceId);
        paramQueryDeviceCapabilities.put("gatewayId", gatewayId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyQueryDeviceCapabilities = httpsUtil.doGetWithParasGetStatusLine(
                urlQueryDeviceCapabilities, paramQueryDeviceCapabilities, header);

        System.out.println("QueryDeviceCapabilities, response content:");
        System.out.println(bodyQueryDeviceCapabilities.getStatusLine());
        System.out.println(bodyQueryDeviceCapabilities.getContent());
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
