package com.yimning.utils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description: 类型转换工具
 * @author: Yimning
 * @create: 2021-04-11 22:53
 **/
public class TypeConversionUtil {
    //Object转Map
    public static Map<String, Object> getObjectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (value == null){
                value = "";
            }
            map.put(fieldName, value);
        }
        return map;
    }
}
