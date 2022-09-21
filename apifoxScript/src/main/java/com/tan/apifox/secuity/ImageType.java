package com.tan.apifox.secuity;

/**
 * @author tanchusheng
 * @version 1.0
 * @description:
 * @date: 2022/9/20 10:54
 */
public enum ImageType {
    PNG('P', 'N', 'G', '-', "PNG"),
    JPG('J', 'F', 'I', 'F', "JPG"),
    GIF('G', 'I', 'F', '-', "GIF");

    private char begin;
    private char mid;
    private char end;
    private char last;
    private String type;

    private ImageType(char begin, char mid, char end, char last, String type) {
        this.begin = begin;
        this.mid = mid;
        this.end = end;
        this.last = last;
        this.type = type;
    }

    public char getBegin() {
        return this.begin;
    }

    public char getMid() {
        return this.mid;
    }

    public char getEnd() {
        return this.end;
    }

    public char getLast() {
        return this.last;
    }

    public String getType() {
        return this.type;
    }

    public static ImageType getImageType(byte[] data) {
        if (PNG.getBegin() == data[1] && PNG.getMid() == data[2] && PNG.getEnd() == data[3]) {
            return PNG;
        } else if (GIF.getBegin() == data[0] && GIF.getMid() == data[1] && GIF.getEnd() == data[2]) {
            return GIF;
        } else {
            return JPG.getBegin() == data[6] && JPG.getMid() == data[7] && JPG.getEnd() == data[8] && JPG.getLast() == data[9] ? JPG : null;
        }
    }
}
