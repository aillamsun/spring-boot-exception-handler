package com.sung.constant;

import com.sung.result.ErrorInfo;
import com.sung.utils.MessageUtils;

/**
 * Created by sungang on 2017/5/19.
 */
public enum ErrorInfoEnum implements ErrorInfo {

    PARAMS_NOT_NULL("000001", "params not null"),
    PARAMS_NOT_NULL2("000002", "params not null2");

    private String code;

    private String message;

    ErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
