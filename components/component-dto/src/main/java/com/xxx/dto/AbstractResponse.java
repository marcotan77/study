package com.xxx.dto;

/**
 * @author tanchusheng
 * @date 2023/6/20
 */
public class AbstractResponse<T> {
    /**
     * 请求ID
     * @mock @guid
     */
    private String requestId;
    private Long requestTime;
    /**
     * 耗时
     * @mock @integer(1,100)
     */
    private Integer cost;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
