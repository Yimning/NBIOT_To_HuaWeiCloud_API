package com.yimning.service.deviceAccess.dataCollection.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceDataInfo;
import com.yimning.entity.DeviceDataInfos;
import com.yimning.service.deviceAccess.appAccessSecurity.Authentication;
import com.yimning.service.deviceAccess.dataCollection.QueryDevicesService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Querying Device Information in Batches :
 * <p>
 * If an NA needs to view detailed information (such as the manufacturer, model, version,
 * status, and service attributes) of multiple devices that have been registered on the
 * IoT platform, the NA can call this API to obtain the information.
 */
@Service
public class QueryDevicesImpl implements QueryDevicesService {
    /**
     *  
     *
     * @Description: 查询所有设备数据信息
     */
    @Override
    public DeviceDataInfos QueryDevices(DeviceDataInfos deviceDataInfos) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        Authentication authentication = new Authentication();
        String accessToken = authentication.accessToken();

        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQueryDevices = Constant.QUERY_DEVICES;

        //please replace the status (ONLINE|OFFLINE|ABNORMAL), when you call this interface.
        String status = "OFFLINE";
        Integer pageNo = 0;
        Integer pageSize = 25;
        Map<String, String> paramQueryDevices = new HashMap<>();
        if (deviceDataInfos.getPageSize() != 0) {
            paramQueryDevices.put("pageSize", Integer.toString(deviceDataInfos.getPageSize()));
        } else paramQueryDevices.put("pageSize", Integer.toString(pageSize));
        paramQueryDevices.put("pageNo", Integer.toString(deviceDataInfos.getPageNo()));
        if (deviceDataInfos.getStatus() != null) {
            paramQueryDevices.put("status", deviceDataInfos.getStatus());
        }
        if (deviceDataInfos.getAppId() != null) {
            paramQueryDevices.put("appId", deviceDataInfos.getAppId());
        }
        if (deviceDataInfos.getGatewayId() != null) {
            paramQueryDevices.put("gatewayId", deviceDataInfos.getGatewayId());
        }
        if (deviceDataInfos.getNodeType() != null) {
            paramQueryDevices.put("nodeType", deviceDataInfos.getNodeType());
        }
        if (deviceDataInfos.getDeviceType() != null) {
            paramQueryDevices.put("deviceType", deviceDataInfos.getDeviceType());
        }
        if (deviceDataInfos.getLocation() != null) {
            paramQueryDevices.put("location", deviceDataInfos.getLocation());
        }
        if (deviceDataInfos.getName() != null) {
            paramQueryDevices.put("name", deviceDataInfos.getName());
        }
        if (deviceDataInfos.getStartTime() != null) {
            paramQueryDevices.put("startTime", deviceDataInfos.getStartTime());
        }
        if (deviceDataInfos.getEndTime() != null) {
            paramQueryDevices.put("endTime", deviceDataInfos.getEndTime());
        }
        if (deviceDataInfos.getSort() != null) {
            paramQueryDevices.put("sort", deviceDataInfos.getSort());
        }
        if (deviceDataInfos.getSort() != null) {
            paramQueryDevices.put("select", deviceDataInfos.getSort());
        }


        System.out.println(paramQueryDevices);
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQueryDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevices,
                paramQueryDevices, header);
        System.out.println("QueryDevices, response content:");
        System.out.println(responseQueryDevices.getStatusLine());
        System.out.println(responseQueryDevices.getContent());
        System.out.println();
        if (responseQueryDevices.getStatusLine().getStatusCode() == 200)
            deviceDataInfos = JSONObject.parseObject(responseQueryDevices.getContent(), DeviceDataInfos.class);
        HttpResponseResult httpResponseResult = new HttpResponseResult();
        httpResponseResult.setStatus_code(responseQueryDevices.getStatusLine().getStatusCode());
        httpResponseResult.setReason_phrase(responseQueryDevices.getStatusLine().getReasonPhrase());
        deviceDataInfos.setHttpResponseResult(httpResponseResult);
        return deviceDataInfos;
    }
}
