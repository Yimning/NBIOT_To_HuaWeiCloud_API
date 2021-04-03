package com.yimning.controller.deviceAccessController.deviceManagementController;

import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.deviceManagement.DeleteDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceManagement")
public class DeleteDeviceController {
	@Autowired
	private DeleteDeviceService deleteDeviceService;

	@PostMapping("/deleteDevice/{deviceId}")
	public HttpResponseResult DeleteDevice(@PathVariable("deviceId")  String deviceId) throws Exception {
		HttpResponseResult httpResponseResult = deleteDeviceService.DeleteDevice(deviceId);
		return httpResponseResult;
	}
}