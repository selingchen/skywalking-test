package com.seling.common.utils;

/**
 * Author: QIQIAN Date: 2017/7/17 Email: qianqi@chinatelecom.cn
 */
public class Result<T> {

	private int success;  // 0 成功，1失败
	private String code;
	private String msg;
	private T data;

	private Result() {
	}

	public Result(T data) {
		this.data = data;
	}
	
	public static <T> Result<T> newInstance() {
		return new Result<>();
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
//	
//	public ResultInfo<T> getRespBody() {
//		return respBody;
//	}
//
//	public void setRespBody(ResultInfo<T> data) {
//		this.respBody = data;
//	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

//	@Override
//	public String toString() {
//		return "Result {success=" + success + ", code=" + code + ", msg=" + msg + ", respBody=" + respBody + "}";
//	}
	@Override
	public String toString() {
//		return "Result {success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + "}";
		return "Result {data=" + data + "}";
	}

}
