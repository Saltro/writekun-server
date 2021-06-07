package com.writekun.server.utils;

public enum ReturnStatus {

    RESPONSE_SUCCESS(1000, "响应正常"),
    RESOURCE_CHANGED(1001, "响应正常，资源已改变");

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
