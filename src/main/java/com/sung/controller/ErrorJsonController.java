package com.sung.controller;

import com.sung.constant.ErrorInfoEnum;
import com.sung.exception.GlobalErrorInfoException;
import com.sung.result.ResultBody;
import com.sung.utils.MessageUtils;
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
     *
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

    /**
     * 测试支持通配符匹配
     * @param account
     * @return
     * @throws GlobalErrorInfoException
     */
    @GetMapping("args")
    public ResultBody findUsers3(String account) throws GlobalErrorInfoException {
        // 入参为空
        if (StringUtils.isEmpty(account)) {
            Object[] args = new Object[]{"啊啊啊啊", "哈哈哈哈哈"};
            throw new GlobalErrorInfoException(ErrorInfoEnum.PARAMS_NOT_NULL2, args);
        }
        return new ResultBody(new User(1L, account, "admin", 18, 1));
    }

    /**
     * 测试全局异常
     * @param account
     * @return
     */
    @GetMapping("other")
    public ResultBody otherExpetion(String account) {
        int i = 1 / 0;
        return new ResultBody(new User(1L, account, "admin", 18, 1));
    }
}
