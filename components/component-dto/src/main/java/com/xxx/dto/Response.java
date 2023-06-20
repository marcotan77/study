package com.xxx.dto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 返回结果
 * @author tanchusheng
 * @date 2023/6/20
 */
public class Response<T> extends AbstractResponse<T> implements Serializable {
    private static final long serialVersionUID = 1352705936429653842L;
    private static final String SUCCESS_MSG = "操作成功";
    private static final String FAIL_MSG = "操作失败";

    /**
     * 异常码
     */
    @NotNull
    private int code = ResponseEnum.SUCCESS.getCode();

    /**
     * 返回信息
     * @mock @pick(["","操作成功"])
     */
    @NotNull
    private String message = "";

    /**
     * 返回值
     */
    private T data;

    @JSONField(serialize = false)
    public  boolean isSuccess() {
        return this.code == ResponseEnum.SUCCESS.getCode();
    }
    /**
     * 成功
     *
     * @return
     */
    public static Response success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return success(data, SUCCESS_MSG);
    }

    /**
     * 成功
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data, String message) {
        Response<T> response = new Response<>();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    /**
     * 失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(T data) {
        return fail(ResponseEnum.SYSTEM_ERROR.getCode(), data, FAIL_MSG);
    }

    /**
     * 失败
     *
     * @param errorMessage
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(String errorMessage, T data) {
        return fail(ResponseEnum.SYSTEM_ERROR.getCode(), data, errorMessage);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param errorMessage
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(int errorCode, String errorMessage) {
        return fail(errorCode, null, errorMessage);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(int errorCode, T data) {
        return fail(errorCode, data, FAIL_MSG);
    }

    /**
     * 失败
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(int errorCode, T data, String message) {
        Response<T> response = new Response<>();
        response.setMessage(message);
        response.setCode(errorCode);
        response.setData(data);

        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
