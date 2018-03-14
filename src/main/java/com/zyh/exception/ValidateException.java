package com.zyh.exception;

/**
 * 验证错误
 *
 * @author      1101399
 * @CreateDate  2018-3-12 下午1:39:10
 */
public class ValidateException extends RuntimeException{

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    public ValidateException(String message){
        super(message);
    }

    public ValidateException(String message, Throwable cause){
        super(message, cause);
    }

    public ValidateException(Throwable cause){
        super(cause);
    }

}
