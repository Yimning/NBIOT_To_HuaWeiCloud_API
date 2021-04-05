package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceCommands;
import com.yimning.entity.DeviceDataInfos;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.commandDelivery.CreateDeviceCommandService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Creating Device Commands :
 * <p>
 * The device profile file defines commands that the IoT platform can deliver to a device.
 * When an NA needs to configure or modify the service attributes of a device, the NA can
 * call this API to deliver commands to the device.
 */
@Service
public class CreateDeviceCommandImpl implements CreateDeviceCommandService {

    @Override
    public DeviceCommands CreateDeviceCommand(DeviceCommands deviceCommands) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String urlCreateDeviceCommand = Constant.CREATE_DEVICE_CMD;
        String appId = Constant.APPID;
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;

        //please replace the deviceId, when you call this interface.
        String deviceId = "e0818ab5-2962-40f2-83de-6dd9ee3569e2";
        //please replace the following parameter values as required, when you call this interface.
        Integer expireTime = 0;
        Integer maxRetransmit = 3;

        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
//        String serviceId = "Transmission";
//        String method = "SET_CONNECTIVITY";
//        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{\"value\":\"0066\"}");
//
//        Map<String, Object> paramCommand = new HashMap<>();
//        paramCommand.put("serviceId", serviceId);
//        paramCommand.put("method", method);
//        paramCommand.put("paras", paras);
//
//        Map<String, Object> paramCreateDeviceCommand = new HashMap<>();
//        paramCreateDeviceCommand.put("deviceId", deviceId);
//        paramCreateDeviceCommand.put("command", paramCommand);
//        paramCreateDeviceCommand.put("callbackUrl", callbackUrl);
//        paramCreateDeviceCommand.put("expireTime", expireTime);
//        paramCreateDeviceCommand.put("maxRetransmit", maxRetransmit);

        String jsonRequest = JsonUtil.jsonObj2Sting(deviceCommands);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        HttpResponse responseCreateDeviceCommand = httpsUtil.doPostJson(urlCreateDeviceCommand, header, jsonRequest);

        String responseBody = httpsUtil.getHttpResponseBody(responseCreateDeviceCommand);

        System.out.println("CreateDeviceCommand, response content:");
        System.out.println(responseCreateDeviceCommand.getStatusLine());
        System.out.println(responseBody);
        System.out.println();
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (responseCreateDeviceCommand.getStatusLine().getStatusCode() == 201)
            deviceCommands = JSONObject.parseObject(responseBody, DeviceCommands.class);
        else {
            httpResponseResult = JSONObject.parseObject(responseBody, HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(responseCreateDeviceCommand.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(responseCreateDeviceCommand.getStatusLine().getReasonPhrase());
        deviceCommands.setHttpResponseResult(httpResponseResult);
        return deviceCommands;
    }
}
