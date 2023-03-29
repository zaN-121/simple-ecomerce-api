package com.enigma.livecodeecomerce.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class SuccessResponse<T> extends BaseResponse{
    private T data;

    public SuccessResponse(String code, String status, String message, T data) {
        super(code, status, message);
        this.data = data;
    }

}
