package com.xxx.bridge;

import com.xxx.bridge.before.PayController;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author tanchusheng
 * @date 2024/7/25 9:50
 */
public class ApiTest_Before {

    @Test
    public void test_pay() {
        PayController pay = new PayController();

        System.out.println("\r\n模拟测试场景；微信支付、人脸方式。");
        pay.doPay("weixin_1092033111", "100000109893", new BigDecimal(100), 1, 2);

        System.out.println("\r\n模拟测试场景；支付宝支付、指纹方式。");
        pay.doPay("jlu19dlxo111","100000109894",new BigDecimal(100), 2, 3);
    }
}
