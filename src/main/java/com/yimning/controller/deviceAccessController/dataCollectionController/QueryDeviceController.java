package com.yimning.controller.deviceAccessController.dataCollectionController;

import java.util.HashMap;
import java.util.Map;

import com.yimning.common.lang.HttpResponseResult;
import com.yimning.entity.DeviceDataInfo;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataCollection")
public class QueryDeviceController {
    @Autowired
    private QueryDeviceService queryDeviceService;

    @GetMapping("/queryDevice")
    public DeviceDataInfo QueryDevice(@RequestParam(name = "deviceId", required = true) String deviceId)throws Exception {
        DeviceDataInfo deviceDataInfo = queryDeviceService.QueryDevice(deviceId);
        return deviceDataInfo;
    }
}