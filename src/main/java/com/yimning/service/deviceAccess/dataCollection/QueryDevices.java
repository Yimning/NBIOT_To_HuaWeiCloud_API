package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Device Information in Batches :
 * 
 * If an NA needs to view detailed information (such as the manufacturer, model, version, 
 * status, and service attributes) of multiple devices that have been registered on the 
 * IoT platform, the NA can call this API to obtain the information.
 */
public class QueryDevices {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDevices = Constant.QUERY_DEVICES;

        //please replace the status (ONLINE|OFFLINE|ABNORMAL), when you call this interface.
        String status = "OFFLINE";
        Integer pageNo = 0;

        Map<String, String> paramQueryDevices = new HashMap<>();
        paramQueryDevices.put("status", status);
        paramQueryDevices.put("pageNo", pageNo.toString());

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevices,
        		paramQueryDevices, header);

        System.out.println("QueryDevices, response content:");
        System.out.println(responseQueryDevices.getStatusLine());
        System.out.println(responseQueryDevices.getContent());
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
