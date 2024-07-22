package com.tan;

import static java.lang.Math.*;

/**
 * @author tanchusheng
 * @date 2023/10/8 16:23
 */
public class x的算数平方根 {

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }


    public static int mySqrt(int x) {
        System.out.println("epx--" + exp(1));

        System.out.println("log--" + log(8));
        System.out.println("pow--" + pow(8, 0.5));


        double ans = exp(0.5 * log(x));

        return (int) ((ans + 1) * (ans + 1) < x ? ans + 1 : ans);


//
//
////        pow(x,0.5);
//        return (int) pow(x,0.5);
    }
}
