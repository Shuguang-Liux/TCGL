package com.tcgl.common.core.exception.user;

import com.tcgl.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author tcgl
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
