package com.enigma.livecodeecomerce.model.response;

public class BaseResponse {
    private String code;
    private String status;
    private String message;

    public BaseResponse(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
