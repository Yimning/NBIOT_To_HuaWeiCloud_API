package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.SendCommand;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.commandDelivery.InvokeDeviceServicesService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Calling Device Services :
 * <p>
 * The device profile file defines commands that the IoT platform can deliver to a device.
 * When an NA needs to configure or modify the service attributes of a device, the NA can
 * call this API to deliver commands to the device.The IoT platform does not cache commands
 * but delivers commands immediately. If a device is offline, the commands fail to be delivered.
 */
@Service
public class InvokeDeviceServicesImpl implements InvokeDeviceServicesService {
    @Override
    public SendCommand InvokeDeviceServices(SendCommand sendCommand) throws Exception {

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
        String deviceId = "e0818ab5-2962-40f2-83de-6dd9ee3569e2";

        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        String serviceId = "Delivery";
        String mode = "ACK";
        String method = "SET_LIGHT_ON";
        String toType = "GATEWAY";

        //Please make sure that the following parameter value has been modified in the Constant file.
        String callbackUrl = Constant.REPORT_CMD_EXEC_RESULT_CALLBACK_URL;
        sendCommand.getHeader().setCallbackURL(callbackUrl);
        sendCommand.getHeader().setToType(toType);
        //please replace the following parameter values, when you call this interface.
        ObjectNode cmdBody = JsonUtil.convertObject2ObjectNode("{\"status\":\"on\"}");
        if (sendCommand.getDeviceId() != null && sendCommand.getServiceId() != null)
            urlInvokeDeviceServices = String.format(urlInvokeDeviceServices, sendCommand.getDeviceId(), sendCommand.getServiceId());
//        Map<String, String> commandNA2CloudHeader = new HashMap<>();
//        commandNA2CloudHeader.put("mode", mode);
//        commandNA2CloudHeader.put("method", method);
//        commandNA2CloudHeader.put("toType", toType);
//        commandNA2CloudHeader.put("callbackURL", callbackUrl);

        Map<String, Object> paramInvokeDeviceServices = new HashMap<>();
        paramInvokeDeviceServices.put("header", sendCommand.getHeader());
        paramInvokeDeviceServices.put("body", sendCommand.getBody());
        System.out.println(paramInvokeDeviceServices);
        String jsonRequest = JsonUtil.jsonObj2Sting(paramInvokeDeviceServices);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseInvokeDeviceServices = httpsUtil.doPostJsonGetStatusLine(urlInvokeDeviceServices, header, jsonRequest);

        System.out.println("InvokeDeviceServices, response content:");
        System.out.println(responseInvokeDeviceServices.getStatusLine());
        System.out.println(responseInvokeDeviceServices.getContent());
        System.out.println();
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (responseInvokeDeviceServices.getStatusLine().getStatusCode() == 202)
            sendCommand = JSONObject.parseObject(responseInvokeDeviceServices.getContent(), SendCommand.class);
        else {
            httpResponseResult = JSONObject.parseObject(responseInvokeDeviceServices.getContent(), HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(responseInvokeDeviceServices.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(responseInvokeDeviceServices.getStatusLine().getReasonPhrase());
        sendCommand.setHttpResponseResult(httpResponseResult);
        return sendCommand;
    }
}
