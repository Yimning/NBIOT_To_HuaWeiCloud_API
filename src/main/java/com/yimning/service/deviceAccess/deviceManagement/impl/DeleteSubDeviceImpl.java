package com.yimning.service.deviceAccess.deviceManagement.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.DeleteSubDeviceService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Deleting a Sub Device :
 * 
 * If a sub device does not need to connect to the IoT platform, an NA can use this API 
 * to unbind the device from the gateway.This function is implemented by issuing commands 
 * to the gateway through the Call Device Services API. 
 */
@Service
public class DeleteSubDeviceImpl implements DeleteSubDeviceService {

    @Override
    public HttpResponseResult DeleteSubDeviceService(String deviceId) throws Exception {
        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        //please replace the deviceId, when you call this interface.
        //String deviceId = "0492bf00-41ca-4f6f-bdfa-409daacd0004";

        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        String serviceId = "Remove";
        String mode = "ACK";
        String method = "REMOVE";
        String toType = "GATEWAY";

        //please replace the callbackURL, when you call this interface.
        String callbackURL = "http://server:port/na/iocm/message/confirm";

        String urlDeleteSubDevice = Constant.DELETE_SUB_DEVICE;
        urlDeleteSubDevice =String.format(urlDeleteSubDevice, deviceId, serviceId);

        Map<String, String> commandHeaderDeleteSubDevice = new HashMap<>();
        commandHeaderDeleteSubDevice.put("mode", mode);
        commandHeaderDeleteSubDevice.put("method", method);
        commandHeaderDeleteSubDevice.put("toType", toType);
        commandHeaderDeleteSubDevice.put("callbackURL", callbackURL);

        Map<String, String> commandBodyDeleteSubDevice = new HashMap<>();
        commandBodyDeleteSubDevice.put("cmdBody", "remove indirect device cmd body content.");

        Map<String, Object> paramDeleteSubDevice = new HashMap<>();
        paramDeleteSubDevice.put("header", commandHeaderDeleteSubDevice);
        paramDeleteSubDevice.put("body", commandBodyDeleteSubDevice);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramDeleteSubDevice);

        StreamClosedHttpResponse responseDeleteSubDevice = httpsUtil
                .doPostJsonGetStatusLine(urlDeleteSubDevice, header, jsonRequest);

        System.out.println("DeleteSubDevice, response content:");
        System.out.println(responseDeleteSubDevice.getStatusLine());
        System.out.println(responseDeleteSubDevice.getContent());
        System.out.println();

        HttpResponseResult responseResult = new HttpResponseResult();
        if(responseDeleteSubDevice.getContent()!=null){
            responseResult = JSONObject.parseObject(responseDeleteSubDevice.getContent(), HttpResponseResult.class);
        }
        responseResult.setStatus_code(responseDeleteSubDevice.getStatusLine().getStatusCode());
        responseResult.setReason_phrase(responseDeleteSubDevice.getStatusLine().getReasonPhrase());
        return responseResult;
    }
}