package com.yimning.controller.deviceAccessController.deviceManagementController;

import com.yimning.entity.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.deviceManagement.ModifyDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Modifying Device Information :
 * 
 * After an NA registers a device with the IoT platform and the basic information 
 * about the device changes, the NA can call this API to modify device information 
 * on the IoT platform.
 */
@RestController
@RequestMapping("/deviceManagement")
public class ModifyDeviceInfoController{
    @Autowired
    private ModifyDeviceInfoService modifyDeviceInfoService;

    @PostMapping("/modifyDeviceInfoService")
    public HttpResponseResult DeleteDevice(@RequestBody Device device) throws Exception {
        HttpResponseResult httpResponseResult = modifyDeviceInfoService.ModifyDeviceInfo(device);
        return httpResponseResult;
    }
}