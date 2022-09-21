package com.tan.apifox.secuity;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author tanchusheng
 * @version 1.0
 * @description:
 * @date: 2022/9/20 10:53
 */
public class Base64Util {
    private static final char last2byte = (char) Integer.parseInt("00000011", 2);
    private static final char last4byte = (char) Integer.parseInt("00001111", 2);
    private static final char last6byte = (char) Integer.parseInt("00111111", 2);
    private static final char lead6byte = (char) Integer.parseInt("11111100", 2);
    private static final char lead4byte = (char) Integer.parseInt("11110000", 2);
    private static final char lead2byte = (char) Integer.parseInt("11000000", 2);
    private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    public Base64Util() {
    }

    public static String encode(byte[] from) {
        StringBuilder to = new StringBuilder((int) ((double) from.length * 1.34) + 3);
        int num = 0;
        char currentByte = 0;

        int i;
        for (i = 0; i < from.length; ++i) {
            for (num %= 8; num < 8; num += 6) {
                switch (num) {
                    case 0:
                        currentByte = (char) (from[i] & lead6byte);
                        currentByte = (char) (currentByte >>> 2);
                    case 1:
                    case 3:
                    case 5:
                    default:
                        break;
                    case 2:
                        currentByte = (char) (from[i] & last6byte);
                        break;
                    case 4:
                        currentByte = (char) (from[i] & last4byte);
                        currentByte = (char) (currentByte << 2);
                        if (i + 1 < from.length) {
                            currentByte = (char) (currentByte | (from[i + 1] & lead2byte) >>> 6);
                        }
                        break;
                    case 6:
                        currentByte = (char) (from[i] & last2byte);
                        currentByte = (char) (currentByte << 4);
                        if (i + 1 < from.length) {
                            currentByte = (char) (currentByte | (from[i + 1] & lead4byte) >>> 4);
                        }
                }

                to.append(encodeTable[currentByte]);
            }
        }

        if (to.length() % 4 != 0) {
            for (i = 4 - to.length() % 4; i > 0; --i) {
                to.append("=");
            }
        }

        return to.toString();
    }

    public static String getImgStrToBase64(String imgStr) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;

        try {
            if (!imgStr.startsWith("http://") && !imgStr.startsWith("https://")) {
                inputStream = new FileInputStream(imgStr);

                int count;
                for (count = 0; count == 0; count = ((InputStream) inputStream).available()) {
                }

                buffer = new byte[count];
                ((InputStream) inputStream).read(buffer);
            } else {
                URL url = new URL(imgStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                inputStream = conn.getInputStream();
                outputStream = new ByteArrayOutputStream();
                buffer = new byte[1024];

                int len;
                while ((len = ((InputStream) inputStream).read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }

                buffer = outputStream.toByteArray();
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    ((InputStream) inputStream).close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

        }

        return (new BASE64Encoder()).encode(buffer);
    }

    public static boolean generateImage(String imgStr) {
        if (imgStr == null) {
            return false;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] b = decoder.decodeBuffer(imgStr);

                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] = (byte) (b[i] + 256);
                    }
                }

                ImageType type = ImageType.getImageType(b);
                System.out.println(type.getType());
                new ByteArrayInputStream(b);
                String imgFilePath = "D:\\tanbing2.jpg";
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
                return true;
            } catch (Exception var7) {
                return false;
            }
        }
    }

    public static InputStream stringToInputStream(String imgStr) {
        if (imgStr == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] b = decoder.decodeBuffer(imgStr);

                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] = (byte) (b[i] + 256);
                    }
                }

                ByteArrayInputStream bai = new ByteArrayInputStream(b);
                return bai;
            } catch (Exception var4) {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        String s = getImgStrToBase64("https://img0.baidu.com/it/u=3382678725,2869780727&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        generateImage(s);
    }
}