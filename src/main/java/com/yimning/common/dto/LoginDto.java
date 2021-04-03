package com.yimning.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: Yimning
 * @date: 2020/7/31  20:55
 * @description:
 */
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "账号不能为空")
    private String userID;
    @NotBlank(message = "密码不能为空")
    private String passWord;
    @NotBlank(message = "请选择用户类型")
    private String userFlag;
}
