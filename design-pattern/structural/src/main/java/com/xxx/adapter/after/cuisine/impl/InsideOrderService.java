package com.xxx.adapter.after.cuisine.impl;


import com.xxx.adapter.after.OrderAdapterService;
import com.xxx.adapter.service.OrderService;

public class InsideOrderService implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    public boolean isFirst(String uId) {
        return orderService.queryUserOrderCount(uId) <= 1;
    }

}
