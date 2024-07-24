package com.xxx.adapter.before;

import com.alibaba.fastjson.JSON;
import com.xxx.adapter.mq.POPOrderDelivered;

public class POPOrderDeliveredService {

    public void onMessage(String message) {

        POPOrderDelivered mq = JSON.parseObject(message, POPOrderDelivered.class);

        mq.getuId();
        mq.getOrderId();
        mq.getOrderTime();

        // ... 处理自己的业务
    }

}
