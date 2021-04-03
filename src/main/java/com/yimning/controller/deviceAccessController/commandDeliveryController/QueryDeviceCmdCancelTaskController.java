package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Command Revocation Tasks :
 * 
 * After an NA creates a command revocation task, the NA can call this API to query 
 * the details and execution status of the task.
 */
public class QueryDeviceCmdCancelTaskController {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceCmdCancelTask = Constant.QUERY_DEVICECMD_CANCEL_TASK;
        
        //please replace the pageSize and startTime, when you call this interface.
        Integer pageSize = 100;
        String startTime = "20190101T121212Z";
        
        Map<String, String> paramQueryDeviceCmdCancelTask = new HashMap<>();
        paramQueryDeviceCmdCancelTask.put("pageSize", pageSize.toString());
        paramQueryDeviceCmdCancelTask.put("startTime", startTime);
        
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseQueryDeviceCmdCancelTask = 
        		httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceCmdCancelTask, paramQueryDeviceCmdCancelTask, header);
        
        System.out.println("QueryDeviceCmdCancelTask, response content:");
        System.out.println(responseQueryDeviceCmdCancelTask.getStatusLine());
        System.out.println(responseQueryDeviceCmdCancelTask.getContent());
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

