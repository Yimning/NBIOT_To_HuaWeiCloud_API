package com.yimning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yimning
 * @since 2020-10-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("studentClass")
    private String studentClass;

    @TableId("studentNumber")
    private String studentNumber;

    @TableField("studentName")
    private String studentName;

    @TableField("studentPassword")
    private String studentPassword;

    @TableField("studentSex")
    private String studentSex;

    @TableField("studentBirth")
    private LocalDate studentBirth;

    @TableField("joinTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinTime;

    private String question;

    private String answer;

    private String profession;

    @TableField("cardNo")
    private String cardNo;

    @TableField("studentAvatar")
    private String studentAvatar;

    @TableField("studentStatus")
    private Integer studentStatus;

    private String face;

    private String groupId;

    private String userId;

    private String logId;

    private String faceToken;

    @TableField("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @TableField("roseID")
    private String roseID;

    @TableField("roseName")
    private String roseName;
}
