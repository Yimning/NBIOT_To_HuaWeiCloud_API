package com.yimning.controller.deviceAccessController.commandDeliveryController;

import com.yimning.entity.QueryDeviceCommands;
import com.yimning.service.deviceAccess.commandDelivery.QueryDeviceCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Querying Device Commands :
 * <p>
 * After an NA delivers a command to a device, the NA can call this API to query
 * the status and content of the delivered command on the IoT platform to check
 * the command execution status.
 */

@RestController
@RequestMapping("/commandDelivery")
public class QueryDeviceCommandsController {
    @Autowired
    private QueryDeviceCommandsService queryDeviceCommandsService;

    @PostMapping("/queryDeviceCommands")
    public Map<String, Object> DeleteDevice(@RequestBody QueryDeviceCommands queryDeviceCommands) throws Exception {
        Map<String, Object> map = queryDeviceCommandsService.QueryDeviceCommands(queryDeviceCommands);
        return map;
    }
}
