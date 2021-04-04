package com.yimning.service.deviceAccess.dataCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimning.entity.DeviceDataInfo;
import com.yimning.entity.DeviceDataInfos;
import com.yimning.entity.QueryDeviceCapabilities;
import com.yimning.utils.Constant;
import com.yimning.utils.HttpsUtil;
import com.yimning.utils.JsonUtil;
import com.yimning.utils.StreamClosedHttpResponse;

/**
 * Querying Service Capabilities of a Device :
 * 
 * If an NA needs to know which service attributes can be reported by a device and 
 * which commands can be delivered to the device, the NA can call this API to query 
 * the device service capabilities defined in the profile file of the device on the 
 * IoT platform.
 */
public interface QueryDeviceCapabilitiesService {
    /** 
     * @Description: 查设备服务能力
     */
    public QueryDeviceCapabilities QueryDeviceCapabilities(QueryDeviceCapabilities queryDeviceCapabilities) throws Exception ;


}
