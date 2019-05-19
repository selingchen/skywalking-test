package com.seling.common.utils;

public class BuildResponse {

	private BuildResponse() {
	}
	
    public static <T> Result<T> buildResult(int success,String code, String msg, T data) {
        Result <T> result = Result.newInstance();
        result.setCode(code);
        result.setSuccess(success);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

//    public static <T> Result<T> buildSuccessResult(ResultInfo<T> respBody) {
//    	return buildResult(0, "200", "success", respBody);
//    }
    
    public static <T> Result<T> buildSuccessResult(T data) {
    	return buildResult(0, "200", "success", data);
    }

    public static <T> Result<T> buildSuccessResult(String msg) {
    	return buildResult(0, "200", msg, null);
    }
    
    public static <T> Result<T> buildSuccessResult(String msg, T data) {
    	return buildResult(0, "200", msg, data);
    }

    
    public static <T> Result<T> buildErrorResult(ErrorCode code) {
        return buildResult(1, code.getCode(), code.getMessage(), null);
    }
    
    public static <T> Result<T> buildErrorResult(ErrorCode code, String message) {
        return buildResult(1, code.getCode(), message, null);
    }

    public static <T> Result<T> buildErrorResult(ErrorCode code, T data) {
        return buildResult(1, code.getCode(), "error", data);
    }
    
    public static <T> Result<T> buildErrorResult(String msg, T data) {
        return buildResult(1, "200", "error", data);
    }
    
    public static <T> Result<T> buildErrorResult(String code, String message) {
	    return  buildResult(1, code, message, null);
    }
    
}
