package com.yimning.controller.deviceAccessController.commandDeliveryController;

import com.yimning.entity.DeviceCommands;

import com.yimning.service.deviceAccess.commandDelivery.CreateDeviceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commandDelivery")
public class CreateDeviceCommandController {
    @Autowired
    private CreateDeviceCommandService createDeviceCommandService;

    @PostMapping("/createDeviceCommand")
    public DeviceCommands DeleteDevice(@RequestBody DeviceCommands deviceCommands) throws Exception {
        deviceCommands = createDeviceCommandService.CreateDeviceCommand(deviceCommands);
        return deviceCommands;
    }
}


