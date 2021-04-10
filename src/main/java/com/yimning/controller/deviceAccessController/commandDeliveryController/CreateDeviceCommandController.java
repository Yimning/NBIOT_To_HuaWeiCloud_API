package com.yimning.controller.deviceAccessController.commandDeliveryController;

import com.yimning.entity.DeviceCommands;

import com.yimning.service.deviceAccess.commandDelivery.CreateDeviceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creating Device Commands :
 * <p>
 * The device profile file defines commands that the IoT platform can deliver to a device.
 * When an NA needs to configure or modify the service attributes of a device, the NA can
 * call this API to deliver commands to the device.
 */

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


