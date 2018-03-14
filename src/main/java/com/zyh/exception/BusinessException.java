package com.zyh.exception;

/**
 * 业务异常对象
 *
 * @author      1101399
 * @CreateDate  2018-3-12 下午1:23:59
 */
public class BusinessException extends RuntimeException{

    /**
     * 序列化 - 默认
     */
    private static final long serialVersionUID = 1L;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message,Throwable cause){
        super(message, cause);
    }

     public BusinessException(Throwable cause){
         super(cause);
     }

}
