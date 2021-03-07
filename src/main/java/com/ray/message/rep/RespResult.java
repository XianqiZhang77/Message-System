package com.ray.message.rep;

import com.ray.message.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespResult {

    private int code;
    private String message;
    private Object data;

    public static RespResult createWithData(Object data) {
        return new RespResult(0, "success", data);
    }

    public static RespResult createWithErrorCode(ErrorCode errorCode) {
        return new RespResult(errorCode.getCode(), errorCode.getMessage(), null);
    }

}
