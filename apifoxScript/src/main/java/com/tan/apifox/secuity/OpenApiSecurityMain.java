package com.tan.apifox.secuity;

import com.jun.security.JunSecUtil;

import java.util.Objects;

/**
 * @author tanchusheng
 * @version 1.0
 * @description: 智运接口平台 加密，解密脚本
 * @date: 2022/9/19 17:04
 */
public class OpenApiSecurityMain {

    // 公钥加密 私钥解密
//    private
    public static void main(String[] args) {
        // 标识:1：加密参数                                                                                                                                                                            ，2：解密，3：加密密钥
        String flag = args[0];
        // 公钥
        String publicKey = args[1];
        // 私钥
        String privateKey = args[2];
        // 加密密钥
        String encryptedKey = args[3];
        // 参数字符串 标识为1时为明文，标识为2是 密文
        String param = args[4];

        if (Objects.equals(flag,"1")){
            System.out.println(encrypt(param, encryptedKey));
        } else if (Objects.equals(flag,"2")){
            System.out.println(decrypt(param,encryptedKey));
        } else if (Objects.equals(flag,"3")) {
            System.out.println(encryptKey(publicKey,encryptedKey));
        }
//        System.out.println(encrypt("测试","JeF8U9wHFOMfs2Y8"));
    }

    /**
     * 通过SM2 加密密钥
     * @param publicKey 公钥
     * @param cipher 密钥
     * @return
     */
    private static String encryptKey(String publicKey, String cipher){
        return JunSecUtil.encryptInSM2(cipher, publicKey);
    }

    /**
     * 加密参数明文
     * @param plainText 明文
     * @param cipher 密钥
     * @return
     */
    private static String encrypt(String plainText, String cipher){
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = cipher;
        sm4.hexString = false;
        return sm4.encryptData_ECB(plainText);
    }


    /**
     * 解密密文
     * @param encryptedText
     * @param cipher
     * @return
     */
    public static String decrypt(String encryptedText, String cipher) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = cipher;
        sm4.hexString = false;
        return sm4.decryptData_ECB(encryptedText);
    }
}
