package com.seling.common.exception;

import com.seling.common.utils.ErrorCode;

public class CommonException extends Exception {
    private final String errorCode;
    private final String errorMsg;

    public CommonException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }

    public CommonException(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


}
