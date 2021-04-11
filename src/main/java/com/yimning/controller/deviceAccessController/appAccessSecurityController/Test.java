package com.yimning.controller.deviceAccessController.appAccessSecurityController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yimning.entity.DeviceCommands;
import org.apache.ibatis.io.ResolverUtil;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description:
 * @author: Yimning
 * @create: 2021-04-10 22:54
 **/
public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode entity = mapper.createObjectNode();
        entity.put("aabc",1231);
//        entity.set("haha", "24324");
//        entity.remove("aabc");
        ObjectNode row = mapper.createObjectNode();
        row.put("0", "a");
        row.put("1"," x");
        row.put("2"," y");
        System.out.println(entity.get("aabc"));
        System.out.println(row);

        // 实例化 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        // json 消息
        String json ="{\"firstname\":\"Bo\",\"lastname\":\"Shang\",\"age\":30,\"hometown\":\"dalian\"}" ;

        // 将 json 转成 JsonNode 对象
        JsonNode rootNode = objectMapper.readTree(json);
        System.out.println(rootNode);


        // 得到节点值
        JsonNode firstNameNode = rootNode.get("firstname");
        System.out.println("firstname:" + firstNameNode.asText());

        JsonNode ageNode = rootNode.get("age");
        System.out.println("age:" + ageNode.asInt());

        // 创建新节点
        ObjectNode newNode = objectMapper.createObjectNode();
        newNode.setAll((ObjectNode)rootNode);
        System.out.println(newNode);

        newNode.put("hometown", "Guangzhou");

        // 将 JsonNode 对象转成 json
        String newjson = objectMapper.writeValueAsString(newNode);
        System.out.println(newjson);

        DeviceCommands commands = new DeviceCommands(json);
        System.out.println(commands);
//        Test t = new Test();
//        t.test1(9,6);
//        System.out.println( t.test1(9,6));
    }
    public int test1(int m,int n){
        int k;
        k=m>n?m:n;
        for(;(k%m!=0)||(k%n!=0);k++);
        return k;
    }

}
