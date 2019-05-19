package com.seling.server04.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.seling.common.exception.BasicExceptionHandler;
import com.seling.common.utils.BuildResponse;
import com.seling.common.utils.Result;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ServerExceptionHandler extends BasicExceptionHandler {


    @ExceptionHandler(value = SeverException.class)
    @ResponseBody
    public Result<String> jsonErrorHandler(HttpServletRequest req, SeverException e) throws Exception {
        return BuildResponse.buildResult(1, e.getErrorCode()+"", e.getErrorMsg(), req.getQueryString());
    }

}

