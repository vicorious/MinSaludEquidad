package com.co.dto;

public class ResponseDTO {

    private int status_code;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status_code=" + status_code +
                '}';
    }
}
