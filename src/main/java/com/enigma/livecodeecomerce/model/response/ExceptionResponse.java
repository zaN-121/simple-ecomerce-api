package com.enigma.livecodeecomerce.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ExceptionResponse extends BaseResponse{
    public ExceptionResponse(String code, String message) {
        super(code, "Failed", message);
    }
}
