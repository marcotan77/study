package com.xxx.dto;

/**
 * 异常码
 * @author tanchusheng
 * @date 2023/6/20
 */
public enum ResponseEnum {
    SUCCESS(0, "操作成功"),
    ERROR_PARAM(-405, "参数校验失败"),

    GW_PARAM_NOT_MATCH(-9993, "参数和方法不匹配"),
    GW_PARAM_ERROR(-9994, "参数格式不正确"),
    GW_METHOD_NOTFOUND(-9995, "方法不存在"),
    GW_SER_ERROR(-9996, "参数%s序列化错误"),
    GW_PARAM_REQUIRED(-9997, "必输参数%s不能为空"),
    GW_INVOKE_ERROR(-9998, "系统异常，请稍后再试！"),//转发给后端服务，后端服务调用失败/异常
    SYSTEM_ERROR(-9999, "系统异常");

    private int code;
    private String value;

    ResponseEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }


    public int getCode() {
        return code;
    }


    public String getValue() {
        return value;
    }
}
