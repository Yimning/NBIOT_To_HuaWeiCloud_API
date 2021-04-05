package com.yimning.service.deviceAccess.dataCollection.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceHistoryData;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceHistoryDataService;
import com.yimning.service.deviceAccess.deviceManagement.DeleteDeviceService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Querying Historical Device Data :
 * <p>
 * The IoT platform receives and saves service data reported by devices during daily
 * operation. If an NA needs to view the historical data reported by a device to the
 * IoT platform, the NA can call this API to obtain the data.
 */
@Service
public class QueryDeviceHistoryDataServiceImpl implements QueryDeviceHistoryDataService {
    /**
     *  
     *
     * @Description: 查询设备数据信息的历史数据
     */
    @Override
    public DeviceHistoryData QueryDeviceHistoryData(DeviceHistoryData deviceHistoryData) throws Exception {
        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDeviceHistoryData = Constant.QUERY_DEVICE_HISTORY_DATA;

        //please replace the deviceId and gatewayId, when you call this interface.
        String deviceId = "9f035e8f-4cc9-4e21-bf97-407953318305";
        String gatewayId = "9f035e8f-4cc9-4e21-bf97-407953318305";
        int pageNo = 0;
        int pageSize = 1;

        Map<String, String> paramQueryDeviceHistoryData = new HashMap<>();

        if (deviceHistoryData.getPageSize() != null) {
            paramQueryDeviceHistoryData.put("pageSize", Integer.toString(deviceHistoryData.getPageSize()));
        } else paramQueryDeviceHistoryData.put("pageSize", Integer.toString(pageSize));

        if (deviceHistoryData.getPageNo() != null) {
            paramQueryDeviceHistoryData.put("pageNo", Integer.toString(deviceHistoryData.getPageNo()));
        } else paramQueryDeviceHistoryData.put("pageNo", Integer.toString(pageNo));

        if (deviceHistoryData.getDeviceId() != null) {
            paramQueryDeviceHistoryData.put("deviceId", deviceHistoryData.getDeviceId());
            paramQueryDeviceHistoryData.put("gatewayId", deviceHistoryData.getDeviceId());
        }

        if (deviceHistoryData.getStartTime() != null) {
            paramQueryDeviceHistoryData.put("startTime", deviceHistoryData.getStartTime());
        }
        if (deviceHistoryData.getEndTime() != null) {
            paramQueryDeviceHistoryData.put("endTime", deviceHistoryData.getEndTime());
        }

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse bodyQueryDeviceHistoryData = httpsUtil.doGetWithParasGetStatusLine(
                urlQueryDeviceHistoryData, paramQueryDeviceHistoryData, header);

        System.out.println("QueryDeviceHistoryData, response content:");
        System.out.println(bodyQueryDeviceHistoryData.getStatusLine());
        System.out.println(bodyQueryDeviceHistoryData.getContent());
        System.out.println();
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        if (bodyQueryDeviceHistoryData.getStatusLine().getStatusCode() == 200)
            deviceHistoryData = JSONObject.parseObject(bodyQueryDeviceHistoryData.getContent(), DeviceHistoryData.class);
        else {
            httpResponseResult = JSONObject.parseObject(bodyQueryDeviceHistoryData.getContent(), HttpResponseResult.class);
        }
        httpResponseResult.setStatus_code(bodyQueryDeviceHistoryData.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(bodyQueryDeviceHistoryData.getStatusLine().getReasonPhrase());
        deviceHistoryData.setHttpResponseResult(httpResponseResult);
        return deviceHistoryData;
    }
}

