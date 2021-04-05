package com.yimning.entity;

import com.yimning.common.lang.HttpResponseResult;

import java.util.List;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 设备的历史数据
 * @author: Yimning
 * @create: 2021-04-05 09:13
 **/
@lombok.Data
public class DeviceHistoryData {
    //访问物联网平台的应用ID，在物联网平台创建应用时由平台分配获得。
    private String app_key;

    //访问物联网平台的认证信息，值为“Bearer {accessToken}”，其中{accessToken}为调用鉴权接口返回的accessToken。
    private String Authorization;

    //设备所属的应用ID，当查询授权应用下设备的历史数据时才需要填写。
    private String appId;

    //设备ID，用于唯一标识一个设备，在注册设备时由物联网平台分配获得。
    private String deviceId;


    //网关ID，用于标识一个网关设备。当设备是直连设备时，gatewayId与设备的deviceId一致。当设备是非直连设备时，gatewayId为设备所关联的直连设备（即网关）的deviceId。
    private String gatewayId;

    //分页查询参数，查询结果分页显示时指定要查看的页信息，
    // 默认0，查询第一页，建议通过设置pageNo和pageSize做分页查询。
    private Integer pageNo;

    //分页查询参数，查询结果分页显示时每页显示的记录数，
    // 默认1，最大值2000，建议根据设备上报数据的平均大小来设置，保证单次查询返回的包大小不超过16Mb，同时根据单次查询返回的包大小以及网络带宽设置合理的超时时间。
    private Integer pageSize;

    //查询产生时间在startTime之后的历史数据。
    // 需要填写UTC时间，格式：yyyyMMdd'T'HHmmss'Z'，如 20151212T121212Z。
    private String startTime;

    //查询产生时间在endTime之前的历史数据。
    // 需要填写UTC时间，格式：yyyyMMdd'T'HHmmss'Z'，如 20151212T121212Z。
    private String endTime;

    private int totalCount;
    private List<DeviceDataHistoryDTOs> deviceDataHistoryDTOs;
    private HttpResponseResult httpResponseResult;

    @lombok.Data
    public static class DeviceDataHistoryDTOs {
        private String deviceId;
        private String gatewayId;
        private String appId;
        private String serviceId;
        private Data data;
        private String timestamp;
    }

}
