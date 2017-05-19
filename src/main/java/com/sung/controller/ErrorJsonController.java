package com.sung.controller;

import com.sung.constant.ErrorInfoEnum;
import com.sung.exception.GlobalErrorInfoException;
import com.sung.result.ResultBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sungang on 2017/5/19.
 */
@RestController
@RequestMapping("api/user")
public class ErrorJsonController {

    /**
     * 获取 用户接口
     * @param account
     * @return
     * @throws GlobalErrorInfoException
     */
    @GetMapping
    public ResultBody findUsers(String account) throws GlobalErrorInfoException {
        // 入参为空
        if (StringUtils.isEmpty(account)) {
            throw new GlobalErrorInfoException(ErrorInfoEnum.PARAMS_NOT_NULL);
        }
        return new ResultBody(new User(1L, account, "admin", 18, 1));
    }
}
