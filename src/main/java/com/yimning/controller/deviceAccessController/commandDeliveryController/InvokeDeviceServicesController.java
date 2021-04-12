package com.yimning.controller.deviceAccessController.commandDeliveryController;

import com.yimning.entity.SendCommand;
import com.yimning.service.deviceAccess.commandDelivery.InvokeDeviceServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commandDelivery")
public class InvokeDeviceServicesController {
    @Autowired
    private InvokeDeviceServicesService invokeDeviceServicesService;

    @PostMapping("/invokeDeviceServices")
    public SendCommand DeleteDevice(@RequestBody SendCommand sendCommand) throws Exception {
        sendCommand = invokeDeviceServicesService.InvokeDeviceServices(sendCommand);
        return sendCommand;
    }
}
