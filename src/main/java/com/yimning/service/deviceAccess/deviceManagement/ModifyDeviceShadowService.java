package com.yimning.service.deviceAccess.deviceManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.common.lang.Device;
import com.yimning.common.lang.HttpResponseResult;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

public interface ModifyDeviceShadowService {
    /** 
     * @Description: 修改设备影子
     */
    public HttpResponseResult ModifyDeviceShadow(String deviceId) throws Exception ;

}