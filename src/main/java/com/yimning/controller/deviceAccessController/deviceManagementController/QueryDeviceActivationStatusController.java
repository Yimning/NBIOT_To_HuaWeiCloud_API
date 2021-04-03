package com.yimning.controller.deviceAccessController.deviceManagementController;


import com.yimning.entity.QueryDeviceActivationStatus;
import com.yimning.service.deviceAccess.deviceManagement.QueryDeviceActivationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceManagement")
public class QueryDeviceActivationStatusController{
    @Autowired
    private QueryDeviceActivationStatusService queryDeviceActivationStatusService;

    @GetMapping("/queryDeviceActivationStatus")
    public QueryDeviceActivationStatus QueryDeviceActivationStatus(@RequestParam(name = "deviceId", required = true) String deviceId) throws Exception {
        QueryDeviceActivationStatus queryDeviceActivationStatus = queryDeviceActivationStatusService.QueryDeviceActivationStatus(deviceId);
        return queryDeviceActivationStatus;
    }
}