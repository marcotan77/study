package com.xxx.c2_abstract_factory;

import com.xxx.c2_abstract_factory.before.CacheService;
import com.xxx.c2_abstract_factory.before.cuisine.impl.CacheServiceImpl;
import org.junit.Test;

/**
 * @author tanchusheng
 * @date 2024/7/23 13:57
 */
public class ApiTest_Before {

    @Test
    public void test_CacheService() {

        CacheService cacheService = new CacheServiceImpl();

        cacheService.set("user_name_01", "小傅哥", 1);
        String val01 = cacheService.get("user_name_01", 1);
        System.out.println("测试结果：" + val01);

    }
}
