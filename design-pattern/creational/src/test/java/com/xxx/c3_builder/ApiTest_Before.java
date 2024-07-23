package com.xxx.c3_builder;

import com.xxx.c3_builder.after.DecorationPackageController;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author tanchusheng
 * @date 2024/7/23 14:31
 */
public class ApiTest_Before {
    @Test
    public void test_DecorationPackageController(){
        DecorationPackageController decoration = new DecorationPackageController();

        // 豪华欧式
        System.out.println(decoration.getMatterList(new BigDecimal("132.52"),1));

        // 轻奢田园
        System.out.println(decoration.getMatterList(new BigDecimal("98.25"),2));

        // 现代简约
        System.out.println(decoration.getMatterList(new BigDecimal("85.43"),3));
    }

}
