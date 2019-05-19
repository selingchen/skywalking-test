package com.seling.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.seling.common.utils.BuildResponse;
import com.seling.common.utils.ErrorCode;
import com.seling.common.utils.Result;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(Exception e) {
        //return BuildResponse.buildErrorResult(ErrorCode.SERVER_ERROR_RUNTIME);//运行时异常
        return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
	public Result illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
		return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getBindingResult().getFieldError().getDefaultMessage());  //参数检查
	}
        
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
	public Result illegalParamsExceptionHandler(ConstraintViolationException e) {
		return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getConstraintViolations().iterator().next().getMessage());  //参数检查
	}
    
    /**
     * JSON中数字格式转换异常
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
	protected Result handleInvalidFormatParameter(InvalidFormatException e) {
		return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getCause().getMessage());
	}
    
    /**
     * 数字越界
     * @param e
     * @return
     */
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
	protected Result handleHttpMessageNotReadableParameter(HttpMessageNotReadableException e) {
		return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getCause().getMessage());
	}
    
    /**
     * 参数类型不匹配
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
	protected Result handleMethodArgumentTypeMismatchParameter(MethodArgumentTypeMismatchException e) {
		return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getCause().getMessage());
	}
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
	protected Result handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
		return BuildResponse.buildResult(1,ErrorCode.PARAM_VARIFY_ERROR.getCode(), 
				ErrorCode.PARAM_VARIFY_ERROR.getMessage(), e.getCause().getMessage());
	}
    
}
