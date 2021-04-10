package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceCommands;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.commandDelivery.CreateDeviceCmdCancelTaskService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

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
@Service
public class CreateDeviceCmdCancelTaskImpl implements CreateDeviceCmdCancelTaskService {
    @Override
    public DeviceCommands CreateDeviceCmdCancelTask(DeviceCommands deviceCommands) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlCreateDeviceCmdCancelTask = Constant.CREATE_DEVICECMD_CANCEL_TASK;

        //please replace the deviceId, when you call this interface.
        String deviceId  = "e0818ab5-2962-40f2-83de-6dd9ee3569e2";

        Map<String, Object> paraCreateDeviceCmdCancelTask = new HashMap<>();
        paraCreateDeviceCmdCancelTask.put("deviceId", deviceId);

        String jsonRequest = JsonUtil.jsonObj2Sting(deviceCommands);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyCreateDeviceCmdCancelTask = httpsUtil.doPostJsonGetStatusLine(urlCreateDeviceCmdCancelTask, header, jsonRequest);

        System.out.println("CreateDeviceCmdCancelTask, response content:");
        System.out.println(bodyCreateDeviceCmdCancelTask.getStatusLine());
        System.out.println(bodyCreateDeviceCmdCancelTask.getContent());
        System.out.println();
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (bodyCreateDeviceCmdCancelTask.getStatusLine().getStatusCode() == 201)
            deviceCommands = JSONObject.parseObject(bodyCreateDeviceCmdCancelTask.getContent(), DeviceCommands.class);
        else {
            httpResponseResult = JSONObject.parseObject(bodyCreateDeviceCmdCancelTask.getContent(), HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(bodyCreateDeviceCmdCancelTask.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(bodyCreateDeviceCmdCancelTask.getStatusLine().getReasonPhrase());
        deviceCommands.setHttpResponseResult(httpResponseResult);
        return deviceCommands;
    }
}
