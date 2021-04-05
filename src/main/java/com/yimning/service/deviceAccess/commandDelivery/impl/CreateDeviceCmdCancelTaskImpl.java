package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Creating Device Command Revocation Tasks :
 *
 * After an NA delivers commands to a device, the IoT platform does not deliver the commands
 * to the device for execution (the commands are in the PENDING state) if the commands are
 * in queue or the device is offline. In this case, the NA can call this API to revoke all
 * the undelivered commands of a specified device. Commands that have been delivered cannot be revoked.
 */
public class CreateDeviceCmdCancelTaskImpl {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlCreateDeviceCmdCancelTask = Constant.CREATE_DEVICECMD_CANCEL_TASK;

        //please replace the deviceId, when you call this interface.
        String deviceId  = "e0818ab5-2962-40f2-83de-6dd9ee3569e2";
        
        Map<String, Object> paraCreateDeviceCmdCancelTask = new HashMap<>();
        paraCreateDeviceCmdCancelTask.put("deviceId", deviceId);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paraCreateDeviceCmdCancelTask);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse bodyCreateDeviceCmdCancelTask = httpsUtil.doPostJsonGetStatusLine(urlCreateDeviceCmdCancelTask, header, jsonRequest);
        
        System.out.println("CreateDeviceCmdCancelTask, response content:");
        System.out.println(bodyCreateDeviceCmdCancelTask.getStatusLine());
        System.out.println(bodyCreateDeviceCmdCancelTask.getContent());
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
