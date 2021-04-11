package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceCommands;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.commandDelivery.ModifyDeviceCommandService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Modifying Device Commands :
 * <p>
 * After an NA delivers a command to a device, the IoT platform does not deliver
 * the command to the device for execution if the command is in queue or the device
 * is offline. In this case, the NA can call this API to modify the command status.
 * Currently, the command status can be changed only to CANCELED, indicating that
 * the command is canceled.
 */
@Service
public class ModifyDeviceCommandImpl implements ModifyDeviceCommandService {

    @Override
    public DeviceCommands ModifyDeviceCommand(DeviceCommands deviceCommands) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        // please replace the commandId, when you call this interface.
        String commandId = "9b513245f915413fba82e0df55282435";
        String urlModifyDeviceCommand = Constant.MODIFY_DEVICE_COMMAND + "/" + commandId;

        //Currently only supports Modify the status of device command from PENDING to CANCELED.
        String status = "CANCELED";

        Map<String, String> paraModifyDeviceCommand = new HashMap<>();
        paraModifyDeviceCommand.put("status", status);
        if (deviceCommands.getCommandId() != null) {
            paraModifyDeviceCommand.put("commandId", deviceCommands.getCommandId());
        }
        if (deviceCommands.getStatus() != null) {
            paraModifyDeviceCommand.put("status", deviceCommands.getStatus());
        }
        if (deviceCommands.getAppId() != null) {
            paraModifyDeviceCommand.put("appId", deviceCommands.getAppId());
        }

        String jsonRequest = JsonUtil.jsonObj2Sting(paraModifyDeviceCommand);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        System.out.println("sdsssssssss"+jsonRequest);
        StreamClosedHttpResponse responseModifyDeviceCommand = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceCommand, header, jsonRequest);

        System.out.println("UpdateAsynCommand, response content:");
        System.out.println(responseModifyDeviceCommand.getStatusLine());
        System.out.println(responseModifyDeviceCommand.getContent());
        System.out.println();

        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (responseModifyDeviceCommand.getStatusLine().getStatusCode() == 200) {
            deviceCommands = JSONObject.parseObject(responseModifyDeviceCommand.getContent(), DeviceCommands.class);
        } else {
            httpResponseResult = JSONObject.parseObject(responseModifyDeviceCommand.getContent(), HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(responseModifyDeviceCommand.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(responseModifyDeviceCommand.getStatusLine().getReasonPhrase());
        deviceCommands.setHttpResponseResult(httpResponseResult);
        return deviceCommands;
    }

}
