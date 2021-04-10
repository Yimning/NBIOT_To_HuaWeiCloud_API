package com.yimning.service.deviceAccess.commandDelivery.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceCommands;
import com.yimning.entity.DeviceHistoryData;
import com.yimning.entity.QueryDeviceCommands;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.commandDelivery.QueryDeviceCmdCancelTaskService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Command Revocation Tasks :
 * 
 * After an NA creates a command revocation task, the NA can call this API to query 
 * the details and execution status of the task.
 */
@Service
public class QueryDeviceCmdCancelTaskImpl implements QueryDeviceCmdCancelTaskService {
    @Override
    public QueryDeviceCommands QueryDeviceCmdCancelTask(QueryDeviceCommands queryDeviceCommands) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceCmdCancelTask = Constant.QUERY_DEVICECMD_CANCEL_TASK;

        //please replace the pageSize and startTime, when you call this interface.
        int pageSize = 1000;
        String startTime = "20190101T121212Z";
        int pageNo = 0;

        Map<String, String> paramQueryDeviceCmdCancelTask = new HashMap<>();
        if (queryDeviceCommands.getPageSize()!= null) {
            paramQueryDeviceCmdCancelTask.put("pageSize", Integer.toString(queryDeviceCommands.getPageSize()));
        } else paramQueryDeviceCmdCancelTask.put("pageSize", Integer.toString(pageSize));

        if (queryDeviceCommands.getPageNo() != null) {
            paramQueryDeviceCmdCancelTask.put("pageNo", Integer.toString(queryDeviceCommands.getPageNo()));
        } else paramQueryDeviceCmdCancelTask.put("pageNo", Integer.toString(pageNo));

        if (queryDeviceCommands.getDeviceId() != null) {
            paramQueryDeviceCmdCancelTask.put("deviceId", queryDeviceCommands.getDeviceId());
        }

        if (queryDeviceCommands.getStartTime() != null) {
            paramQueryDeviceCmdCancelTask.put("startTime", queryDeviceCommands.getStartTime());
        }
        if (queryDeviceCommands.getEndTime() != null) {
            paramQueryDeviceCmdCancelTask.put("endTime", queryDeviceCommands.getEndTime());
        }
        if (queryDeviceCommands.getStatus() != null) {
            paramQueryDeviceCmdCancelTask.put("status", queryDeviceCommands.getStatus());
        }
        if (queryDeviceCommands.getAppId() != null) {
            paramQueryDeviceCmdCancelTask.put("appId", queryDeviceCommands.getAppId());
        }

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQueryDeviceCmdCancelTask =
                httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceCmdCancelTask, paramQueryDeviceCmdCancelTask, header);

        System.out.println("QueryDeviceCmdCancelTask, response content:");
        System.out.println(responseQueryDeviceCmdCancelTask.getStatusLine());
        System.out.println(responseQueryDeviceCmdCancelTask.getContent());
        System.out.println();

        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (responseQueryDeviceCmdCancelTask.getStatusLine().getStatusCode() == 200)
            queryDeviceCommands = JSONObject.parseObject(responseQueryDeviceCmdCancelTask.getContent(), QueryDeviceCommands.class);
        else {
            httpResponseResult = JSONObject.parseObject(responseQueryDeviceCmdCancelTask.getContent(), HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(responseQueryDeviceCmdCancelTask.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(responseQueryDeviceCmdCancelTask.getStatusLine().getReasonPhrase());
        queryDeviceCommands.setHttpResponseResult(httpResponseResult);
        return queryDeviceCommands;
    }
}

