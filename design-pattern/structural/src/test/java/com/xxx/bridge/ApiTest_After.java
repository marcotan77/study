package com.xxx.bridge;

import com.xxx.bridge.after.pay.channel.Pay;
import com.xxx.bridge.after.pay.channel.WxPay;
import com.xxx.bridge.after.pay.channel.ZfbPay;
import com.xxx.bridge.after.pay.mode.PayFaceMode;
import com.xxx.bridge.after.pay.mode.PayFingerprintMode;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author tanchusheng
 * @date 2024/7/25 9:50
 */
public class ApiTest_After {
    @Test
    public void test_pay() {

        System.out.println("\r\n模拟测试场景；微信支付、人脸方式。");
        Pay wxPay = new WxPay(new PayFaceMode());
        wxPay.transfer("weixin_1092033111", "100000109893", new BigDecimal(100));

        System.out.println("\r\n模拟测试场景；支付宝支付、指纹方式。");
        Pay zfbPay = new ZfbPay(new PayFingerprintMode());
        zfbPay.transfer("jlu19dlxo111","100000109894",new BigDecimal(100));

    }
}
