package com.yimning.controller.deviceAccessController.dataCollectionController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimning.entity.Device;
import com.yimning.entity.DeviceDataInfo;
import com.yimning.entity.DeviceDataInfos;
import com.yimning.service.deviceAccess.dataCollection.QueryDeviceService;
import com.yimning.service.deviceAccess.dataCollection.QueryDevicesService;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataCollection")
public class QueryDevicesController {
    @Autowired
    private QueryDevicesService queryDevicesService;

    @PostMapping("/queryDevices")
    public List<DeviceDataInfo> QueryDevices(@RequestBody DeviceDataInfos DeviceDataInfos)throws Exception {
        List<DeviceDataInfo> deviceDataInfos = queryDevicesService.QueryDevices(DeviceDataInfos);
        return deviceDataInfos;
    }
}