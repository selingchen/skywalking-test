package com.seling.common.utils;

public enum ErrorCode {
	SQL_ERROR("410100", "mysql服务失效"),
	SQL_TIMEOUT_ERROR("410101", "mysql连接超时"),
	SQL_GET_NULL("410102", "未查找到结果"),
	SQL_SAVE_ERROR("410103", "数据保存失败"),
	SERVER_NOT_AVALIABLE("410104","服务当前不可用"),
	SERVER_ERROR_RUNTIME("410110","服务器异常"),
	PARAM_SIZE_ERROR("610201","参数个数不匹配"),
	PARAM_VARIFY_ERROR("610202","参数验证失败"),
	AUTHORITY_PERMISSION_DENY("",""),
	VALIDATION_ERROR("",""),
	PLAN_NOT_EXIST("404005","测试计划不存在")
	;

	private String code;
	private String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ErrorCode getErrorCode(String code) {
		for (ErrorCode errorCode : ErrorCode.values()) {
			if (errorCode.getCode().equals(code))
				return errorCode;
		}
		return null;
	}
}
