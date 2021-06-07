package com.writekun.server.utils;

import lombok.Data;

@Data
public class JSONReturnBody<T> {
    private int statusCode;
    private String statusMsg;
    private T data;

    public JSONReturnBody() {}

    public JSONReturnBody(ReturnStatus rs) {
        statusCode = rs.getStatusCode();
        statusMsg = rs.getStatusMsg();
    }

    public JSONReturnBody(ReturnStatus rs, T obj) {
        statusCode = rs.getStatusCode();
        statusMsg = rs.getStatusMsg();
        data = obj;
    }

    public void setStatus(ReturnStatus rs) {
        statusCode = rs.getStatusCode();
        statusMsg = rs.getStatusMsg();
    }

    public void setData(T data) {
        this.data = data;
    }
}
