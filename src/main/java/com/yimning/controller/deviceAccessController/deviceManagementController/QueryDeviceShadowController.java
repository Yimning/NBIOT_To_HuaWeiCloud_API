package com.yimning.controller.deviceAccessController.deviceManagementController;


import com.yimning.entity.QueryDeviceShadow;
import com.yimning.service.deviceAccess.deviceManagement.QueryDeviceShadowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceManagement")
public class QueryDeviceShadowController {
    @Autowired
    private QueryDeviceShadowService queryDeviceShadowService;

    @GetMapping("/queryDeviceShadow")
    public QueryDeviceShadow QueryDeviceShadow(@RequestParam(name = "deviceId", required = true) String deviceId) throws Exception {
        QueryDeviceShadow queryDeviceShadow = queryDeviceShadowService.QueryDeviceShadow(deviceId);
        return queryDeviceShadow;
    }
}