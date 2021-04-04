package com.yimning.service.deviceAccess.dataCollection.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceCapabilitiesService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Service Capabilities of a Device :
 * <p>
 * If an NA needs to know which service attributes can be reported by a device and
 * which commands can be delivered to the device, the NA can call this API to query
 * the device service capabilities defined in the profile file of the device on the
 * IoT platform.
 */
@Service
public class QueryDeviceCapabilitiesImpl implements QueryDeviceCapabilitiesService {
    /**
     *  
     *
     * @Description: 查询设备服务能力
     */
    @Override
    public QueryDeviceCapabilities QueryDeviceCapabilities(QueryDeviceCapabilities queryDeviceCapabilities) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceCapabilities = Constant.QUERY_DEVICE_CAPABILITIES;

        //please replace the deviceId and gatewayId, when you call this interface.
        //String deviceId = "14dc5d95-c306-415d-8aec-1afb6e797c19";
        //String gatewayId = "14dc5d95-c306-415d-8aec-1afb6e797c19";

        Map<String, String> paramQueryDeviceCapabilities = new HashMap<>();
        if (queryDeviceCapabilities.getDeviceId() != null)
            paramQueryDeviceCapabilities.put("deviceId", queryDeviceCapabilities.getDeviceId());
        if (queryDeviceCapabilities.getGatewayId() != null)
            paramQueryDeviceCapabilities.put("gatewayId", queryDeviceCapabilities.getGatewayId());

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyQueryDeviceCapabilities = httpsUtil.doGetWithParasGetStatusLine(
                urlQueryDeviceCapabilities, paramQueryDeviceCapabilities, header);

        System.out.println("QueryDeviceCapabilities, response content:");
        System.out.println(bodyQueryDeviceCapabilities.getStatusLine());
        System.out.println(bodyQueryDeviceCapabilities.getContent());
        System.out.println();
        if (bodyQueryDeviceCapabilities.getStatusLine().getStatusCode() == 200) {
            queryDeviceCapabilities = JSONObject.parseObject(bodyQueryDeviceCapabilities.getContent(), QueryDeviceCapabilities.class);
        }
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        httpResponseResult.setStatus_code(bodyQueryDeviceCapabilities.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(bodyQueryDeviceCapabilities.getStatusLine().getReasonPhrase());
        queryDeviceCapabilities.setHttpResponseResult(httpResponseResult);
        return queryDeviceCapabilities;
    }
}
