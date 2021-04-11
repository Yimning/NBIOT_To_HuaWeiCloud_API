package com.yimning.controller.deviceAccessController.commandDeliveryController;

import com.yimning.entity.DeviceCommands;
import com.yimning.service.deviceAccess.commandDelivery.ModifyDeviceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/commandDelivery")
public class ModifyDeviceCommandController {
    @Autowired
    private ModifyDeviceCommandService modifyDeviceCommandService;

    @PostMapping("/modifyDeviceCommand")
    public DeviceCommands DeleteDevice(@RequestBody DeviceCommands deviceCommands) throws Exception {
        deviceCommands = modifyDeviceCommandService.ModifyDeviceCommand(deviceCommands);
        return deviceCommands;
    }
}
