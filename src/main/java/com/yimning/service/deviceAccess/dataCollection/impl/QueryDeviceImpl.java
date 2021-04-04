package com.yimning.service.deviceAccess.dataCollection.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceDataInfo;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Information About a Device :
 * <p>
 * If an NA needs to view detailed information (such as the manufacturer, model, version,
 * status, and service attributes) of a device that has been registered on the IoT platform,
 * the NA can call this API to obtain the information.
 */

@Service
public class QueryDeviceImpl implements QueryDeviceService {

    @Override
    public DeviceDataInfo QueryDevice(String deviceId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the deviceId, when you call this interface.
        //String deviceId = "14dc5d95-c306-415d-8aec-1afb6e797c19";
        String urlQueryDevice = Constant.QUERY_DEVICE + "/" + deviceId;

        Map<String, String> paramQueryDevice = new HashMap<>();
        paramQueryDevice.put("appId", appId);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyQueryDevice = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevice,
                paramQueryDevice, header);

        System.out.println("QueryDevice, response content:");
        System.out.println(bodyQueryDevice.getStatusLine());
        System.out.println(bodyQueryDevice.getContent());
        System.out.println();

        DeviceDataInfo deviceDataInfo = new DeviceDataInfo();
        if (!(bodyQueryDevice.getContent().isEmpty())) {
            deviceDataInfo = JSONObject.parseObject(bodyQueryDevice.getContent(), DeviceDataInfo.class);
        }
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        httpResponseResult.setStatus_code(bodyQueryDevice.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(bodyQueryDevice.getStatusLine().getReasonPhrase());
        deviceDataInfo.setHttpResponseResult(httpResponseResult);
        return deviceDataInfo;
    }
}
