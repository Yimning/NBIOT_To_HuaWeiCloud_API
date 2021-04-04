package com.yimning.controller.deviceAccessController.dataCollectionController;

import java.util.HashMap;
import java.util.Map;

import com.yimning.entity.DeviceDataInfo;
import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceCapabilitiesService;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataCollection")
public class QueryDeviceCapabilitiesController {
    @Autowired
    private QueryDeviceCapabilitiesService queryDeviceCapabilitiesService;

    @PostMapping("/queryDeviceCapabilities")
    public QueryDeviceCapabilities QueryDevice(@RequestBody QueryDeviceCapabilities queryDeviceCapabilities)throws Exception {
         queryDeviceCapabilities = queryDeviceCapabilitiesService.QueryDeviceCapabilities(queryDeviceCapabilities);
        return queryDeviceCapabilities;
    }
}