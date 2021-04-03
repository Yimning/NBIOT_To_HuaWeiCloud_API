package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Information About a Device :
 * 
 * If an NA needs to view detailed information (such as the manufacturer, model, version, 
 * status, and service attributes) of a device that has been registered on the IoT platform, 
 * the NA can call this API to obtain the information.
 * 
 */
public class QueryDevice {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the deviceId, when you call this interface.
        String deviceId = "9f035e8f-4cc9-4e21-bf97-407953318305";
        String urlQueryDevice = Constant.QUERY_DEVICE + "/" + deviceId;

        Map<String, String> paramQueryDevice = new HashMap<>();
        paramQueryDevice.put("appId", appId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyQueryDevice = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevice,
        		paramQueryDevice, header);

        System.out.println("QueryDevice, response content:");
        System.out.println(bodyQueryDevice.getStatusLine());
        System.out.println(bodyQueryDevice.getContent());
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
