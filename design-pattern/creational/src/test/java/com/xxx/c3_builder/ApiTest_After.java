package com.xxx.c3_builder;

import com.xxx.c3_builder.before.Builder;
import org.junit.Test;

/**
 * @author tanchusheng
 * @date 2024/7/23 14:31
 */
public class ApiTest_After {

    @Test
    public void test_Builder(){
        Builder builder = new Builder();

        // 豪华欧式
        System.out.println(builder.levelOne(132.52D).getDetail());

        // 轻奢田园
        System.out.println(builder.levelTwo(98.25D).getDetail());

        // 现代简约
        System.out.println(builder.levelThree(85.43D).getDetail());
    }
}
