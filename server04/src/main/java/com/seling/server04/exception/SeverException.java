package com.seling.server04.exception;

import com.seling.common.utils.ErrorCode;

public class SeverException extends Exception {
    
    private final String errorCode;
    private final String errorMsg;

    public SeverException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }

    public SeverException(ErrorCode errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMessage();
    }
    
    public SeverException(String errorCode, String errorMsg) {
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