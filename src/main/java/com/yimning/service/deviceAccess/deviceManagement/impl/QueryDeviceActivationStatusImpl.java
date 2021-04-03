package com.yimning.service.deviceAccess.deviceManagement.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.QueryDeviceActivationStatus;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.QueryDeviceActivationStatusService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Device Activation Status :
 * 
 * After an NA registers a device on the IoT platform, the activation status of the device 
 * is false before the device connects to the IoT platform for the first time. When the 
 * device connects to the IoT platform for the first time, the activation status of the device 
 * is true regardless of whether the device is online, offline, or abnormal. The NA can call 
 * this API to query the activation status of the device to check whether the device has 
 * connected to the IoT platform.
 */

@Service
public class QueryDeviceActivationStatusImpl implements QueryDeviceActivationStatusService {

    @Override
    public QueryDeviceActivationStatus QueryDeviceActivationStatus(String deviceId) throws Exception {
        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the deviceId, when you call this interface.
        String urlDeviceActivationStatus = Constant.QUERY_DEVICE_ACTIVATION_STATUS + "/" + deviceId;

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyDeviceActivationStatus = httpsUtil.doGetWithParasGetStatusLine(
                urlDeviceActivationStatus, null, header);

        System.out.println("QueryDeviceActivationStatus, response content:");
        System.out.println(bodyDeviceActivationStatus.getStatusLine());
        System.out.println(bodyDeviceActivationStatus.getContent());
        System.out.println();
        QueryDeviceActivationStatus queryDeviceActivationStatus = new QueryDeviceActivationStatus();
        if(bodyDeviceActivationStatus.getContent()!=null){
            queryDeviceActivationStatus = JSONObject.parseObject(bodyDeviceActivationStatus.getContent(), QueryDeviceActivationStatus.class);
        }
        queryDeviceActivationStatus.setStatus_code(bodyDeviceActivationStatus.getStatusLine().getStatusCode());
        queryDeviceActivationStatus.setReason_phrase(bodyDeviceActivationStatus.getStatusLine().getReasonPhrase());

        return  queryDeviceActivationStatus;
    }
}
