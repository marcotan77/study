package com.tan.apifox.secuity;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanchusheng
 * @version 1.0
 * @description:
 * @date: 2022/9/20 10:45
 */
public class SM4Utils {
    public String secretKey = "";
    private String iv = "";
    public boolean hexString = false;

    public SM4Utils() {
    }

    public String encryptData_ECB(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 1;
            byte[] keyBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(this.secretKey);
            } else {
                keyBytes = this.secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            String cipherText = (new BASE64Encoder()).encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }

            return cipherText;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public String decryptData_ECB(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 0;
            byte[] keyBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(this.secretKey);
            } else {
                keyBytes = this.secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, (new BASE64Decoder()).decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public String encryptData_CBC(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 1;
            byte[] keyBytes;
            byte[] ivBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(this.secretKey);
                ivBytes = Util.hexStringToBytes(this.iv);
            } else {
                keyBytes = this.secretKey.getBytes();
                ivBytes = this.iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("GBK"));
            String cipherText = (new BASE64Encoder()).encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }

            return cipherText;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = 0;
            byte[] keyBytes;
            byte[] ivBytes;
            if (this.hexString) {
                keyBytes = Util.hexStringToBytes(this.secretKey);
                ivBytes = Util.hexStringToBytes(this.iv);
            } else {
                keyBytes = this.secretKey.getBytes();
                ivBytes = this.iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, (new BASE64Decoder()).decodeBuffer(cipherText));
            return new String(decrypted, "GBK");
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String plainText = Base64Util.getImgStrToBase64("https://img0.baidu.com/it/u=3382678725,2869780727&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        plainText = "JeF8U9wHFOMfs2Y8";
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = "JeF8U9wHFOMfs2Y8";
        sm4.hexString = false;
        plainText = "{\"appId\": \"db4d6376bd03b19e889725176b1c0854\"}";
        plainText = "{\n    \"pageIndex\":1,\n    \"pageSize\":20\n}";
        System.out.println("ECB模式加密");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文:" + cipherText);
    }
}

