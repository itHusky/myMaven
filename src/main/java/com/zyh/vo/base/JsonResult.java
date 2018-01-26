package com.zyh.vo.base;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * JsonResult 传递类封装
 *
 * @author 1101399
 * @CreateDate 2018-1-17 上午9:33:12
 * @param <T>
 */
@JsonSerialize
public class JsonResult<T> {
    private boolean status;// 返回状态
    private String message;// 返回信息
    private HashMap<String, String> errors = new HashMap<String, String>();// 每个输入错误的消息
    private T entity;// 返回数据

    /**
     * 初始化 对于Java而言如果有带参的构造函数则必须有一个不带参的构造函数
     */
    public JsonResult() {
        super();
    }

    /**
     * JsonResult 构造函数
     *
     * @param status
     *            状态
     */
    public JsonResult(boolean status) {
        super();
        this.setStatus(status);
    }

    /**
     * JsonResult 构造函数
     *
     * @param status
     *            状态
     * @param message
     *            信息
     * @param entity
     *            数据
     */
    public JsonResult(boolean status, String message, T entity) {
        super();
        this.setStatus(status);
        this.setMessage(message);
        this.setEntity(entity);
    }

    /*
     * 若 T 为String时 会造成构造函数不明确的bug 切记!
     *
     * public JsonResult(boolean status, T entity) {
     *      super();
     *      this.setStatus(status);
     *      this.setEntity(entity);
     * }
     */

    /**
     * JsonResult 构造函数
     *
     * @param status
     *            状态
     * @param message
     *            信息
     */
    public JsonResult(boolean status, String message) {
        super();
        this.setStatus(status);
        this.setMessage(message);
    }

    public void check() throws RuntimeException {
        if (getErrors().size() > 0) {
            setStatus(false);
            throw new RuntimeException("JsonResult有错误发生");
        } else {
            setStatus(true);
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
