package com.tan.apifox.secuity;

/**
 * @author tanchusheng
 * @version 1.0
 * @description:
 * @date: 2022/9/20 10:46
 */
public class SM4_Context {
    public int mode = 1;
    public long[] sk = new long[32];
    public boolean isPadding = true;

    public SM4_Context() {
    }
}
