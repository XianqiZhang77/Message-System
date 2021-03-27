package com.ray.message.exception;

import com.ray.message.rep.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    protected RespResult handleServiceException(ServiceException ex) {
        return RespResult.createWithErrorCode(ex.getErrorCode());
    }

}
