package com.yimning.controller.deviceAccessController.appAccessSecurityController;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: JsonNode练习
 * @author: Yimning
 * @create: 2021-04-11 20:44
 **/
public class JsonNode {

    public static void main(String[] args) throws JsonProcessingException {
        String data = "{\n" +
                "  \"checked\": false,\n" +
                "  \"dimensions\": {\n" +
                "    \"width\": 5,\n" +
                "    \"height\": 10\n" +
                "  },\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"A green door\",\n" +
                "  \"price\": 12.5,\n" +
                "  \"tags\": [\n" +
                "    \"home\",\n" +
                "    \"green\"\n" +
                "  ]\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(data, JSONObject.class, Feature.OrderedField);
        JSONObject jsonObject1 = JSONObject.parseObject(data);

        System.out.println(jsonObject);
        System.out.println(jsonObject1);

    }
}