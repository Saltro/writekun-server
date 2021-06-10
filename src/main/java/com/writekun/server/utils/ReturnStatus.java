package com.writekun.server.utils;

public enum ReturnStatus {

    RESPONSE_SUCCESS(1000, "响应正常"),
    RESOURCE_CHANGED(1001, "响应正常，资源已改变"),

    NOTE_ERROR(2000, "笔记异常"),
    NOTE_NOT_FOUND(2001, "笔记未找到"),

    USER_LOGIN_SUCCESS(3000, "登录成功"),
    USER_ACCOUNT_EXPIRED(3001, "账号过期"),
    USER_PASSWORD_ERROR(3002, "密码错误"),
    USER_PASSWORD_EXPIRED(3003, "密码过期"),
    USER_ACCOUNT_DISABLE(3004, "密码不可用"),
    USER_ACCOUNT_LOCKED(3005, "账号锁定"),
    USER_ACCOUNT_NOT_EXIST(3006, "账号不存在"),
    USER_NOT_LOGIN(3007, "用户未登录"),
    USERNAME_ALREADY_EXISTS(3008, "用户名已存在"),
    USER_ERROR(3009, "用户未知错误"),


    UNKNOWN_ERROR(9000, "未知错误");

    private int statusCode;
    private String statusMsg;

    ReturnStatus(int statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    @Override
    public String toString() {
        return "StatusInfo{" +
                "statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                '}';
    }
}
