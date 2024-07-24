package com.xxx.adapter.after.cuisine.impl;


import com.xxx.adapter.after.OrderAdapterService;
import com.xxx.adapter.service.POPOrderService;

public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    public boolean isFirst(String uId) {
        return popOrderService.isFirstOrder(uId);
    }

}
