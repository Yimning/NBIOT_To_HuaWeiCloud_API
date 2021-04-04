package com.yimning.controller.deviceAccessController.deviceManagementController;

import com.yimning.entity.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.deviceManagement.RegisterDeviceWithPasswordModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceManagement")
public class RegisterDeviceWithPasswordModeController {
    @Autowired
    private RegisterDeviceWithPasswordModeService registerDeviceWithPasswordModeService;

    @PostMapping("/registerDeviceWithPasswordMode")
    public HttpResponseResult DeleteDevice(@RequestBody Device device) throws Exception {
        HttpResponseResult httpResponseResult = registerDeviceWithPasswordModeService.RegisterDeviceWithPasswordMode(device);
        return httpResponseResult;
    }

}
