package com.yimning.service.deviceManagement.subscribtionManagement;

import java.util.HashMap;
import java.util.Map;

import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Subscription in Batches :
 * 
 * An NA can subscribe to different types of device change notifications on the IoT platform. 
 * The NA can call this API to query all subscription configurations of the current application 
 * or of a specified subscription type.
 */
public class QuerySubscriptionsController {

    public static void main(String args[]) throws Exception {

        // Two-Way Authentication
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();

        // Authentication.get token
        String accessToken = login(httpsUtil);
        
        //Please make sure that the following parameter values have been modified in the Constant file.
        String appId = Constant.APPID;
        String urlQuerySubscriptions = Constant.QUERY_SUBSCRIPTIONS;
        
        /*
         * please replace the subscriptionId, when you call this interface.
         * service Notify Type
         * deviceAdded|bindDevice|deviceInfoChanged|deviceDataChanged|deviceDatasChanged|
         * deviceDeleted|messageConfirm|commandRsp|deviceEvent|serviceInfoChanged|
         * ruleEvent|deviceModelAdded|deviceModelDeleted|
         * deviceDesiredPropertiesModifyStatusChanged
         * management Notify Type
         * swUpgradeStateChangeNotify|swUpgradeResultNotify|fwUpgradeStateChangeNotify|
         * fwUpgradeResultNotify
         */
        String notifyType = "deviceAdded";
        
        Map<String, String> paramQuerySubscriptions = new HashMap<>();
        paramQuerySubscriptions.put("notifyType", notifyType);

        Map<String, String> header = new HashMap<>();
        header.put(Constant.HEADER_APP_KEY, appId);
        header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

        StreamClosedHttpResponse responseQuerySubscriptions = httpsUtil.doGetWithParasGetStatusLine(
        		urlQuerySubscriptions, paramQuerySubscriptions, header);

        System.out.println("QuerySubscriptions, response content:");
        System.out.println(responseQuerySubscriptions.getStatusLine());
        System.out.println(responseQuerySubscriptions.getContent());
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
