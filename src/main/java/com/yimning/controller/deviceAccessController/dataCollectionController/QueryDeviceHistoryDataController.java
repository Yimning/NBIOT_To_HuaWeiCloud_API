package com.yimning.controller.deviceAccessController.dataCollectionController;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceHistoryData;
import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceCapabilitiesService;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceHistoryDataService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataCollection")
public class QueryDeviceHistoryDataController {
    @Autowired
    private QueryDeviceHistoryDataService queryDeviceHistoryDataService;

    @PostMapping("/queryDeviceHistoryData")
    public DeviceHistoryData QueryDevice(@RequestBody DeviceHistoryData  deviceHistoryData)throws Exception {
        deviceHistoryData = queryDeviceHistoryDataService.QueryDeviceHistoryData(deviceHistoryData);
        return deviceHistoryData;
    }
}