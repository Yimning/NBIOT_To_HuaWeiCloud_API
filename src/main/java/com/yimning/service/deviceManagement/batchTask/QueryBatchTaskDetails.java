package com.yimning.service.deviceManagement.batchTask;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Information About a Subtask of a Batch Task :
 * 
 * After creating a batch task for devices, an NA can call this API to query information 
 * about a subtask of the batch task, including the subtask execution status and subtask content.
 */
public class QueryBatchTaskDetails {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryBatchTaskDetails = Constant.QUERY_BATCH_TASK_DETAILS;

        //please replace the taskId, when you call this interface.
        String taskId = "5c26d34878965909ca44c1f0";

        Map<String, String> paramQueryBatchTaskDetails = new HashMap<>();
        paramQueryBatchTaskDetails.put("taskId", taskId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse ResponseQueryBatchTaskDetails = httpsUtil.doGetWithParasGetStatusLine(urlQueryBatchTaskDetails,
        		paramQueryBatchTaskDetails, header);

        System.out.println("QueryBatchTaskDetails, response content:");
        System.out.println(ResponseQueryBatchTaskDetails.getStatusLine());
        System.out.println(ResponseQueryBatchTaskDetails.getContent());
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
