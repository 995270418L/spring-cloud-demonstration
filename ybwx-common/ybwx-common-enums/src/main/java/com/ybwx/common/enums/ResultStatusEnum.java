package com.ybwx.common.enums;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2017/10/10
 * @时间 :	17:23
 */
public enum ResultStatusEnum {

    OK(0, "OK"),
    NOT_LOGGED_IN(101, "未登录"),
    SIGNIN_SUCCESS(102, "登录成功"),
    SIGNIN_FAILURE(103, "登录失败"),
    SIGNOUT_SUCCESS(104, "登出成功"),
    UNAUTHORIZED(105, "没有权限"),
    NOT_FOUND(106, "资源不存在"),
    ERROR(107, "服务异常"),
    DATA_NO_EXIST(109, "数据不存在"),
    QUESTIONS_TARGET_CANNOT_SPLIT(201, "目标角色类型的问题不允许分拆角色."),
    SERVICE_INFO(110, "生产者服务异常"),
    UNPROCESSABLE_ENTITY(422, "请求参数错误");

    private Integer code;
    private String message;

    ResultStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
