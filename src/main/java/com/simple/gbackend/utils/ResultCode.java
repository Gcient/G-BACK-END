package com.simple.gbackend.utils;




public enum ResultCode {

    SUCCESS(200,"success"),
    ERROR(100,"error"),
    USER_LOGIN_SUCCESS(200,"用户登录成功"),
    USER_OR_PASSWORD_ERROR(101,"用户名或密码错误"),
    USER_OR_PASSWORD_FORMAT(102,"用户名或密码格式错误"),
    USER_OR_PASSWORD_EMPTY(103,"用户名或密码为空"),
    USER_CACHE_TIMEOUT(104,"用户缓存超时"),
    USER_INFO_EMPTY(105,"用户信息为空"),
    PASSWORD_CHECK_ERROR(106,"两次密码不同"),
    MYSQL_SAVE_ERROR(107,"数据库保存失败"),
    NO_PERMISSION(108,"用户无权限");




    private Integer code;
    private String message;



    ResultCode(Integer code,String message){
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
