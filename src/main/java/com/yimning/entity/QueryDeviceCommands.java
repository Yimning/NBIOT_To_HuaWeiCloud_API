package com.yimning.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.common.lang.HttpResponseResult;
import lombok.Data;

import java.util.List;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 查询设备命令
 * @author: Yimning
 * @create: 2021-04-10 20:59
 **/
@lombok.Data
public  class QueryDeviceCommands {
    private String app_key;
    private String Authorization;
    private Integer pageNo;
    private Integer pageSize;
    private String taskId;
    private String appId;
    private String deviceId;  //命令所属的应用ID，当创建授权应用下的命令时才需要填写。
    private String status;
    private String commandId;
    private String startTime;
    private String endTime;
    private HttpResponseResult httpResponseResult;
    private Pagination pagination;
    private List<DeviceCommandTaskResp> data;


    @lombok.Data
    public static class Pagination {
        private int pageNo;
        private int pageSize;
        private int totalSize;
    }

    @lombok.Data
    public static class DeviceCommandTaskResp {
        private String commandId;
        private String appId;
        private String deviceId;
        private DeviceCommands.Command command;
        private int expireTime;
        private String status;
        private Result result;
        private String creationTime;
        private String platformIssuedTime;
        private int issuedTimes;
        private int maxRetransmit;

        private String taskId;
        private int totalCount;
        //设备命令撤销任务信息列表。
        private List<DeviceCommandResp> deviceCommands;
    }

    @lombok.Data
    public static class DeviceCommandResp {
        private String commandId;
        private String appId;
        private String deviceId;
        private DeviceCommands.Command command;
        private String callbackUrl;
        private String expireTime;
        private String status;
        private String result;
        private String creationTime;
        private int executeTime;
        private String platformIssuedTime;
        private String deliveredTime;
        private int issuedTimes;
        private int maxRetransmit;
    }


    @lombok.Data
    public static class Result {
        private String reason;
    }

}
