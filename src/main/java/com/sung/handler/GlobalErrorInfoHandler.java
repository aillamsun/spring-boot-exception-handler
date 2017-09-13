package com.sung.handler;

import com.sung.exception.GlobalErrorInfoException;
import com.sung.result.ErrorInfo;
import com.sung.result.ResultBody;
import com.sung.utils.MessageUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一错误码异常处理
 * <p>
 * Created by sungang on 2017/5/19.
 */

@RestControllerAdvice
public class GlobalErrorInfoHandler {


    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, GlobalErrorInfoException exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        getMessage(errorInfo,exception.getArgs());
        ResultBody result = new ResultBody(errorInfo);
        return result;
    }


    private void getMessage(ErrorInfo errorInfo,Object...agrs) {
        String message = null;
        if (!StringUtils.isEmpty(errorInfo.getCode())) {
            message = MessageUtils.message(errorInfo.getCode(), agrs);
        }
        if (message == null) {
            message = errorInfo.getMessage();
        }
        errorInfo.setMessage(message);
    }
}