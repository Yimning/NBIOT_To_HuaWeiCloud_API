package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Calling Device Services :
 * 
 * The device profile file defines commands that the IoT platform can deliver to a device. 
 * When an NA needs to configure or modify the service attributes of a device, the NA can 
 * call this API to deliver commands to the device.The IoT platform does not cache commands 
 * but delivers commands immediately. If a device is offline, the commands fail to be delivered.
 *
 */
public class InvokeDeviceServicesImpl {

    public static void main(String[] args) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlInvokeDeviceServices = Constant.INVOKE_DEVICE_SERVICES;
        String appId = Constant.APPID;

        //please replace the deviceId and serviceId, when you call this interface.
        String deviceId = "9e620731-9a8b-42b7-b685-263546b74afc";
        
        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        String serviceId = "Delivery";
        String mode = "ACK";
        String method = "SET_LIGHT_ON";
        String toType = "GATEWAY";
        
        //Please make sure that the following parameter value has been modified in the Constant file.
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
        
        //please replace the following parameter values, when you call this interface.
        ObjectNode cmdBody = JsonUtil.convertObject2ObjectNode("{\"status\":\"on\"}");
        
        urlInvokeDeviceServices = String.format(urlInvokeDeviceServices, deviceId, serviceId);
        
        Map<String, String> commandNA2CloudHeader = new HashMap<>();
        commandNA2CloudHeader.put("mode", mode);
        commandNA2CloudHeader.put("method", method);
        commandNA2CloudHeader.put("toType", toType);
        commandNA2CloudHeader.put("callbackURL", callbackUrl);  
        
        Map<String, Object> paramInvokeDeviceServices = new HashMap<>();
        paramInvokeDeviceServices.put("header", commandNA2CloudHeader);
        paramInvokeDeviceServices.put("body", cmdBody);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paramInvokeDeviceServices);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseInvokeDeviceServices = httpsUtil.doPostJsonGetStatusLine(urlInvokeDeviceServices, header, jsonRequest);

        System.out.println("InvokeDeviceServices, response content:");
        System.out.println(responseInvokeDeviceServices.getStatusLine());
        System.out.println(responseInvokeDeviceServices.getContent());
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
