package com.yimning.entity;

import java.util.List;

import com.yimning.common.lang.HttpResponseResult;
import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 所有设备的信息和数据情况
 * @author: Yimning
 * @create: 2021-04-04 14:04
 **/
@Data
public class DeviceDataInfos {
    private int totalCount;
    private int pageNo;
    private int pageSize;
    private String appId;
    private String gatewayId;
    private String deviceId;
    private String nodeType;
    private String deviceType;
    private String location;
    private String name;
    private String status;//设备的状态，表示设备是否在线，取值范围：ONLINE、OFFLINE、INACTIVE、ABNORMAL。
    private String startTime;
    private String endTime;
    private String sort;  //指定返回记录的排序。缺省值：DESC
                        //ASC：按注册设备的时间升序排列
                        // DESC：按注册设备的时间降序排列
    private String select;//指定返回记录，可取值：imsi。
    private HttpResponseResult httpResponseResult;
    private List<Devices> devices;

    @Data
    public class Devices {
        private String deviceId;
        private String gatewayId;
        private String nodeType;
        private String createTime;
        private String lastModifiedTime;
        private DeviceInfo deviceInfo;
        private List<DeviceService> services;
        private AlarmInfoDTO alarmInfo;
        @Data
        public class DeviceService {
            private String serviceId;
            private String serviceType;
            private ServiceInfo serviceInfo;
            private String eventTime;
            @Data
            public class ServiceInfo {
                List<String> muteCmds;
            }
        }
        @Data
        public class AlarmInfoDTO {
            private String alarmSeverity;
            private Boolean alarmStatus;
            private String alarmTime;
        }

    }
}