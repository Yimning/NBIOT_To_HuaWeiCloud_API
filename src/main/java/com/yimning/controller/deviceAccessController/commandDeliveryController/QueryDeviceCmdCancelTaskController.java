package com.yimning.controller.deviceAccessController.commandDeliveryController;

import com.yimning.entity.QueryDeviceCommands;
import com.yimning.service.deviceAccess.commandDelivery.QueryDeviceCmdCancelTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commandDelivery")
public class QueryDeviceCmdCancelTaskController {
    @Autowired
    private QueryDeviceCmdCancelTaskService queryDeviceCmdCancelTaskService;

    @PostMapping("/queryDeviceCmdCancelTask")
    public QueryDeviceCommands DeleteDevice(@RequestBody QueryDeviceCommands queryDeviceCommands) throws Exception {
        queryDeviceCommands = queryDeviceCmdCancelTaskService.QueryDeviceCmdCancelTask(queryDeviceCommands);
        return queryDeviceCommands;
    }
}
