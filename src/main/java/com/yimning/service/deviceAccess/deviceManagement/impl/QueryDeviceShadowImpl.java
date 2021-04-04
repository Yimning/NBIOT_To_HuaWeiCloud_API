package com.yimning.service.deviceAccess.deviceManagement.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.entity.QueryDeviceShadow;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.deviceManagement.QueryDeviceShadowService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Device Shadow Information :
 * 
 * The NA can call this API to check the device configuration information and the latest 
 * data reported by the device on the device shadow.
 */
@Service
public class QueryDeviceShadowImpl implements QueryDeviceShadowService {

    @Override
    public QueryDeviceShadow QueryDeviceShadow(String deviceId) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;

        //please replace the deviceId, when you call this interface.
       // String deviceId = "14dc5d95-c306-415d-8aec-1afb6e797c19";
        String urlQueryDeviceShadow = Constant.QUERY_DEVICE_SHADOW + "/" + deviceId;

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQueryDeviceShadow = httpsUtil.doGetWithParasGetStatusLine(
                urlQueryDeviceShadow, null, header);

        System.out.println("QueryDeviceShadow, response content:");
        System.out.println(responseQueryDeviceShadow.getStatusLine());
        System.out.println(responseQueryDeviceShadow.getContent());
        System.out.println();
        QueryDeviceShadow queryDeviceShadow = new QueryDeviceShadow();
        if(responseQueryDeviceShadow.getContent()!=null){
            queryDeviceShadow = JSONObject.parseObject(responseQueryDeviceShadow.getContent(), QueryDeviceShadow.class);
        }
        queryDeviceShadow.setStatus_code(responseQueryDeviceShadow.getStatusLine().getStatusCode());
        queryDeviceShadow.setReason_phrase(responseQueryDeviceShadow.getStatusLine().getReasonPhrase());

        return  queryDeviceShadow;
    }
}
