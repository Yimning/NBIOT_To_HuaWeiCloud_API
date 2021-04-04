package com.yimning.controller.deviceAccessController.deviceManagementController;


import com.yimning.entity.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.deviceManagement.RegisterDeviceWithVerificationCodeModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceManagement")
public class RegisterDeviceWithVerificationCodeModeController {
    @Autowired
    private RegisterDeviceWithVerificationCodeModeService registerDeviceWithVerificationCodeModeService;

    @PostMapping("/registerDeviceWithVerificationCodeMode")
    public HttpResponseResult DeleteDevice(@RequestBody Device device) throws Exception {
        HttpResponseResult httpResponseResult = registerDeviceWithVerificationCodeModeService.RegisterDeviceWithVerificationCodeMode(device);
        return httpResponseResult;
    }

}
