package com.tan.test;

/**
 *
 * @author tanchusheng
 * @date 2024/1/24 10:47
 */
public class RouteDO {
    private String orderNo;
    private Integer routeType;
    private Integer routeOrder;
    private String lotLat;
    private double distance;
    private String address;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public String getLotLat() {
        return lotLat;
    }

    public void setLotLat(String lotLat) {
        this.lotLat = lotLat;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(Integer routeOrder) {
        this.routeOrder = routeOrder;
    }

    public RouteDO() {
    }

    public RouteDO(String orderNo, Integer routeType,Integer routeOrder, String lotLat,double distance,String address) {
        this.orderNo = orderNo;
        this.routeType = routeType;
        this.lotLat = lotLat;
        this.distance = distance;
        this.address = address;
        this.routeOrder = routeOrder;
    }

}
