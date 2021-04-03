package com.yimning.controller.deviceAccessController.deviceManagementController;

import com.yimning.common.lang.HttpResponseResult;
import com.yimning.service.deviceAccess.deviceManagement.DeleteSubDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceManagement")
public class DeleteSubDeviceController {
	@Autowired
	private DeleteSubDeviceService deleteSubDeviceService;

	@PostMapping("/deleteSubDeviceService/{deviceId}")
	public HttpResponseResult DeleteDevice(@PathVariable("deviceId")  String deviceId) throws Exception {
		HttpResponseResult httpResponseResult = deleteSubDeviceService.DeleteSubDeviceService(deviceId);
		return httpResponseResult;
	}
}