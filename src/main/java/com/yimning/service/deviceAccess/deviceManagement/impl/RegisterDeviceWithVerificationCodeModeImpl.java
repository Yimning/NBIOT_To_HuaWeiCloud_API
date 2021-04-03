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
 * Registering a Device (Verification Code Mode) :
 * <p>
 * Before connecting a device to the IoT platform, an NA must call this API to register
 * the device on the IoT platform and set a unique identification code (such as IMEI) for
 * the device. Then, the device can use the unique identification code to get authenticated
 * and connect to the IoT platform.
 */
@Service
public class RegisterDeviceWithVerificationCodeModeImpl implements RegisterDeviceWithVerificationCodeModeService {

    @Override
    public HttpResponseResult RegisterDeviceWithVerificationCodeMode(Device device) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlRegisterDevice = Constant.REGISTER_DEVICE_VERIFICATIONCODE;

        //please replace the verifyCode and nodeId and timeout, when you call this interface.
        String verifyCode = device.getVerifyCode();
        String nodeId = verifyCode;
        Integer timeout = 0;
        String manufacturerId = null;
        String manufacturerName = null;
        String deviceType = null;
        String model = null;
        String protocolType = null;
        String deviceName = null;

        //please replace the following parameter values, when you call this interface.
        //And those parameter values must be consistent with the content of profile that have been preset to IoT platform.
        //The following parameter values of this demo are use the watermeter profile that already initialized to IoT platform.
        if (device.getDeviceInfo() != null) {
            manufacturerId = device.getDeviceInfo().getManufacturerId();
            manufacturerName = device.getDeviceInfo().getManufacturerName();
            deviceType = device.getDeviceInfo().getDeviceType();
            model = device.getDeviceInfo().getModel();
            protocolType = device.getDeviceInfo().getProtocolType();
        }

        Map<String, Object> paramDeviceInfo = new HashMap<>();
        paramDeviceInfo.put("manufacturerId", manufacturerId);
        paramDeviceInfo.put("manufacturerName", manufacturerName);
        paramDeviceInfo.put("deviceType", deviceType);
        paramDeviceInfo.put("model", model);
        paramDeviceInfo.put("protocolType", protocolType);

        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("deviceInfo", paramDeviceInfo);
        paramReg.put("timeout", timeout);
        if(device.getDeviceName()!=null){
            System.out.println(device.getDeviceName());
            paramReg.put("deviceName", device.getDeviceName());
        }


        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseRegisterDevice = httpsUtil.doPostJsonGetStatusLine(urlRegisterDevice, header, jsonRequest);

        System.out.println("RegisterDevice, response content:");
        System.out.println(responseRegisterDevice.getStatusLine());
        System.out.println(responseRegisterDevice.getContent());
        System.out.println();

        HttpResponseResult responseResult = new HttpResponseResult();
        if (responseRegisterDevice.getContent()!=null){
            responseResult	= JSONObject.parseObject(responseRegisterDevice.getContent(), HttpResponseResult.class);
        }
        responseResult.setStatus_code(responseRegisterDevice.getStatusLine().getStatusCode());
        responseResult.setReason_phrase(responseRegisterDevice.getStatusLine().getReasonPhrase());
        return responseResult;
    }
}
