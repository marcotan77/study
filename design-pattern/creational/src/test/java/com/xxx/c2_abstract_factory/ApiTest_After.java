package com.xxx.c2_abstract_factory;

import com.xxx.c2_abstract_factory.after.CacheService;
import com.xxx.c2_abstract_factory.after.factory.JDKProxy;
import com.xxx.c2_abstract_factory.after.factory.impl.EGMCacheAdapter;
import com.xxx.c2_abstract_factory.after.factory.impl.IIRCacheAdapter;
import com.xxx.c2_abstract_factory.after.impl.CacheServiceImpl;
import org.junit.Test;

/**
 * @author tanchusheng
 * @date 2024/7/23 13:57
 */
public class ApiTest_After {

    @Test
    public void test_CacheService() throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "小傅哥");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "小傅哥");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);

    }
}
