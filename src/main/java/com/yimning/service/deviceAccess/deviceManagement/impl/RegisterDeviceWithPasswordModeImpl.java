package com.yimning.service.deviceAccess.deviceManagement.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.RegisterDeviceWithPasswordModeService;
import com.yimning.service.deviceAccess.deviceManagement.RegisterDeviceWithVerificationCodeModeService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Registering a Device (Password Mode) :
 * 
 * Before connecting a device to the IoT platform, the NA must call this API to register 
 * the device on the IoT platform and obtain the device ID and secret. Then, the device 
 * can use the device ID and secret to get authenticated and connect to the IoT platform.
 */
@Service
public class RegisterDeviceWithPasswordModeImpl implements RegisterDeviceWithPasswordModeService {

    @Override
    public HttpResponseResult RegisterDeviceWithPasswordMode(Device device) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlRegisterDevice = Constant.REGISTER_DEVICE_PASSWORD;

        //please replace the verifyCode and nodeId and timeout, when you call this interface.
        Boolean mqttConnect = true;
        String secret = device.getSecret();

        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        String nodeId = device.getDeviceInfo().getNodeId();
        String manufacturerId= device.getDeviceInfo().getManufacturerId();
        String manufacturerName = device.getDeviceInfo().getManufacturerName();
        String deviceType = device.getDeviceInfo().getDeviceType();
        String model = device.getDeviceInfo().getModel();
        String protocolType = device.getDeviceInfo().getModel();
        String name = device.getDeviceInfo().getName();
        String region = device.getRegion();

        Map<String, Object> paramDeviceInfo = new HashMap<>();
        paramDeviceInfo.put("nodeId", nodeId);
        paramDeviceInfo.put("manufacturerId", manufacturerId);
        paramDeviceInfo.put("manufacturerName", manufacturerName);
        paramDeviceInfo.put("deviceType", deviceType);
        paramDeviceInfo.put("model", model);
        paramDeviceInfo.put("protocolType", protocolType);
        paramDeviceInfo.put("name", name);
        paramDeviceInfo.put("region", region);

        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("deviceInfo", paramDeviceInfo);
        paramReg.put("mqttConnect", mqttConnect);
        // paramReg.put("secret", secret);

        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseRegisterDevice = httpsUtil.doPostJsonGetStatusLine(urlRegisterDevice, header, jsonRequest);

        System.out.println("RegisterDevice, response content:");
        System.out.println(responseRegisterDevice.getStatusLine());
        System.out.println(responseRegisterDevice.getContent());
        System.out.println();
        HttpResponseResult responseResult = JSONObject.parseObject(responseRegisterDevice.getContent(), HttpResponseResult.class);
        responseResult.setStatus_code(responseRegisterDevice.getStatusLine().getStatusCode());
        responseResult.setReason_phrase(responseRegisterDevice.getStatusLine().getReasonPhrase());
        return responseResult;
    }
}
