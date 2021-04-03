package com.yimning.common.lang;

import lombok.Data;

/**
 * @program: NBIOT_To_HuaWeiCloud_API
 * @description:返回结果集
 * @author: Yimning
 * @create: 2021-04-02 22:02
 **/

@Data
public class HttpResponseResult {

    private int error_code;
    private String error_desc;
    private int status_code;
    private String reason_phrase;
}
