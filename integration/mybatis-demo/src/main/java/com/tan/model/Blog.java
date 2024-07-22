package com.tan.model;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author tanchusheng
 * @date 2023/10/20 11:24
 */
public class Blog {

    public static void main(String[] args) {
        Integer a = 1;
        System.out.println(BigDecimal.valueOf(Optional.ofNullable(a).orElse(0)));
    }
}
