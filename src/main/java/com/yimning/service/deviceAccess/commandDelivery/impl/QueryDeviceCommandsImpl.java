package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceCommands;
import com.yimning.entity.QueryDeviceCommands;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.commandDelivery.QueryDeviceCommandsService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Querying Device Commands :
 * <p>
 * After an NA delivers a command to a device, the NA can call this API to query
 * the status and content of the delivered command on the IoT platform to check
 * the command execution status.
 */
@Service
public class QueryDeviceCommandsImpl implements QueryDeviceCommandsService {

    @Override
    public QueryDeviceCommands QueryDeviceCommands(QueryDeviceCommands queryDeviceCommands) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceCMD = Constant.QUERY_DEVICE_CMD;

        //please replace the pageSize and startTime, when you call this interface.
        int pageSize = 1000;
        String startTime = "20190101T121212Z";
        int pageNo = 0;


        Map<String, String> paramQueryDeviceCommands = new HashMap<>();
        if (queryDeviceCommands.getPageSize() != null) {
            paramQueryDeviceCommands.put("pageSize", Integer.toString(queryDeviceCommands.getPageSize()));
        } else paramQueryDeviceCommands.put("pageSize", Integer.toString(pageSize));

        if (queryDeviceCommands.getPageNo() != null) {
            paramQueryDeviceCommands.put("pageNo", Integer.toString(queryDeviceCommands.getPageNo()));
        } else paramQueryDeviceCommands.put("pageNo", Integer.toString(pageNo));

        if (queryDeviceCommands.getDeviceId() != null) {
            paramQueryDeviceCommands.put("deviceId", queryDeviceCommands.getDeviceId());
        }

        if (queryDeviceCommands.getStartTime() != null) {
            paramQueryDeviceCommands.put("startTime", queryDeviceCommands.getStartTime());
        }
        if (queryDeviceCommands.getEndTime() != null) {
            paramQueryDeviceCommands.put("endTime", queryDeviceCommands.getEndTime());
        }
        if (queryDeviceCommands.getStatus() != null) {
            paramQueryDeviceCommands.put("status", queryDeviceCommands.getStatus());
        }
        if (queryDeviceCommands.getAppId() != null) {
            paramQueryDeviceCommands.put("appId", queryDeviceCommands.getAppId());
        }

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQueryDeviceCMD = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceCMD, paramQueryDeviceCommands, header);

        System.out.println("QueryDeviceCommands, response content:");
        System.out.println(responseQueryDeviceCMD.getStatusLine());
        System.out.println(responseQueryDeviceCMD.getContent());
        System.out.println();
        String json = null;
        int index = responseQueryDeviceCMD.getContent().indexOf("\"paras\":{\""); //找到第一个空格所在的索引
        System.out.println(index);

        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (responseQueryDeviceCMD.getStatusLine().getStatusCode() == 200) {
                queryDeviceCommands = JSONObject.parseObject(responseQueryDeviceCMD.getContent(), QueryDeviceCommands.class);
        } else {
            httpResponseResult = JSONObject.parseObject(responseQueryDeviceCMD.getContent(), HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(responseQueryDeviceCMD.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(responseQueryDeviceCMD.getStatusLine().getReasonPhrase());
        queryDeviceCommands.setHttpResponseResult(httpResponseResult);

        return queryDeviceCommands;
    }
}