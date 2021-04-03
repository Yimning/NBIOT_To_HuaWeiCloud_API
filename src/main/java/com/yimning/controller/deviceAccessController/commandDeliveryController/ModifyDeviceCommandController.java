package com.yimning.service.deviceAccess.commandDelivery;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Modifying Device Commands :
 * 
 * After an NA delivers a command to a device, the IoT platform does not deliver 
 * the command to the device for execution if the command is in queue or the device 
 * is offline. In this case, the NA can call this API to modify the command status. 
 * Currently, the command status can be changed only to CANCELED, indicating that 
 * the command is canceled.
 */
public class ModifyDeviceCommandController {

	public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);

        //Please make sure that the following parameter values have been modified in the Constant file.
		String appId = Constant.APPID;

		 // please replace the commandId, when you call this interface.
        String commandId = "aa0413242212451aa08fd53c1693807a";
        String urlModifyDeviceCommand = Constant.MODIFY_DEVICE_COMMAND + "/" + commandId;

        //Currently only supports Modify the status of device command from PENDING to CANCELED.
        String status = "CANCELED";
        
        Map<String, String> paraModifyDeviceCommand = new HashMap<>();
        paraModifyDeviceCommand.put("status", status);
        
        String jsonRequest = JsonUtil.jsonObj2Sting(paraModifyDeviceCommand);
                
        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        
        StreamClosedHttpResponse responseModifyDeviceCommand = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceCommand, header, jsonRequest);
        
        System.out.println("UpdateAsynCommand, response content:");
		System.out.println(responseModifyDeviceCommand.getStatusLine());
		System.out.println(responseModifyDeviceCommand.getContent());
		System.out.println();
		
        
    }

    /**
     * Authentication.get token
     */
    @SuppressWarnings("unchecked")
    public static String login(HttpsUtil httpsUtil) throws Exception {

        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        String urlLogin = Constant.APP_AUTH;

        Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("appId", appId);
        paramLogin.put("secret", secret);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, paramLogin);

        System.out.println("app auth success,return accessToken:");
        System.out.println(responseLogin.getStatusLine());
        System.out.println(responseLogin.getContent());
        System.out.println();

        Map<String, String> data = new HashMap<>();
        data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
        return data.get("accessToken");
    }

}
