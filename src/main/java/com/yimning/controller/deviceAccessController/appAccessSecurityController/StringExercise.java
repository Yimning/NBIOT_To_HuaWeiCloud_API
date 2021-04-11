package com.yimning.controller.deviceAccessController.appAccessSecurityController;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 字符串操作练习
 * @author: Yimning
 * @create: 2021-04-11 16:29
 **/
public class StringExercise {
    public static void main(String[] args) {
        String json = "{\"pagination\":{\"pageNo\":0,\"pageSize\":1000,\"totalSize\":5},\"data\":[{\"commandId\":\"cb91bbe16c2649efacdbfbc6fcedb133\",\"appId\":\"O8syWKWBEMGUO7j0mqOskvH6x2Ea\",\"deviceId\":\"e0818ab5-2962-40f2-83de-6dd9ee3569e2\",\"command\":{\"serviceId\":\"Transmission\",\"method\":\"SET_CONNECTIVITY\",\"paras\":{\"LED\":\"ON\",\"COLOR\":\"YELLOW\"}},\"expireTime\":0,\"status\":\"FAILED\",\"result\":{\"reason\":\"send command failed\"},\"creationTime\":\"20210405T143335Z\",\"platformIssuedTime\":\"20210405T143335Z\",\"issuedTimes\":0,\"maxRetransmit\":0},{\"commandId\":\"9b513245f915413fba82e0df55282435\",\"appId\":\"O8syWKWBEMGUO7j0mqOskvH6x2Ea\",\"deviceId\":\"e0818ab5-2962-40f2-83de-6dd9ee3569e2\",\"command\":{\"serviceId\":\"Transmission\",\"method\":\"SET_CONNECTIVITY\",\"paras\":{\"value\":\"0066\"}},\"callbackUrl\":\"http://10.10.10.99:9999/na/iocm/devNotify/v1.1.0/reportCmdExecResult\",\"expireTime\":0,\"status\":\"FAILED\",\"result\":{\"reason\":\"send command failed\"},\"creationTime\":\"20210405T124021Z\",\"platformIssuedTime\":\"20210405T124021Z\",\"issuedTimes\":0,\"maxRetransmit\":3},{\"commandId\":\"5ed49b5442144ca4b308b873b2f88d8f\",\"appId\":\"O8syWKWBEMGUO7j0mqOskvH6x2Ea\",\"deviceId\":\"14dc5d95-c306-415d-8aec-1afb6e797c19\",\"command\":{\"serviceId\":\"TempHum\",\"method\":\"CmdWork\",\"paras\":{\"value\":\"1\"}},\"callbackUrl\":\"http://10.10.10.99:9999/na/iocm/devNotify/v1.1.0/reportCmdExecResult\",\"expireTime\":0,\"status\":\"TIMEOUT\",\"creationTime\":\"20210405T120358Z\",\"platformIssuedTime\":\"20210405T120358Z\",\"issuedTimes\":0,\"maxRetransmit\":3},{\"commandId\":\"dc0b78138cce4a95a679068ba1b699ad\",\"appId\":\"O8syWKWBEMGUO7j0mqOskvH6x2Ea\",\"deviceId\":\"14dc5d95-c306-415d-8aec-1afb6e797c19\",\"command\":{\"serviceId\":\"TempHum\",\"method\":\"CmdWork\",\"paras\":{\"cmdValue\":1}},\"expireTime\":0,\"status\":\"DELIVERED\",\"creationTime\":\"20210405T120225Z\",\"platformIssuedTime\":\"20210405T120226Z\",\"deliveredTime\":\"20210405T120226Z\",\"issuedTimes\":0},{\"commandId\":\"18c2169f127c439c92f77a514e46ffac\",\"appId\":\"O8syWKWBEMGUO7j0mqOskvH6x2Ea\",\"deviceId\":\"14dc5d95-c306-415d-8aec-1afb6e797c19\",\"command\":{\"serviceId\":\"TempHum\",\"method\":\"CmdWork\",\"paras\":{\"value\":\"1\"}},\"callbackUrl\":\"http://10.10.10.99:9999/na/iocm/devNotify/v1.1.0/reportCmdExecResult\",\"expireTime\":0,\"status\":\"DELIVERED\",\"creationTime\":\"20210405T115651Z\",\"platformIssuedTime\":\"20210405T115652Z\",\"deliveredTime\":\"20210405T115652Z\",\"issuedTimes\":0,\"maxRetransmit\":3}]}\n";


        String strArray[] = json.split("\"paras\":");// 拆分的结果保存到字符串数组中
        System.out.println(strArray[0]);
        System.out.println(strArray[1]);
        int index1 = json.indexOf("\"paras\":{"); //找到第一个空格所在的索引
        int index2 = json.indexOf("}", 100); //找到索引4以后的第一个空格所在索引
        String result = json.substring(index1, index2 + 1);//实际索引号[beginIndex,EndIndex-1]
        System.out.println(result);
        json = json.replace(result, "\"paras\":{}");
        System.out.println(json);
    }
}
