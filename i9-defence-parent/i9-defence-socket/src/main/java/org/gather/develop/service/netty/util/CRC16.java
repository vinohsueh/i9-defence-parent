package org.gather.develop.service.netty.util;

import java.io.UnsupportedEncodingException;

public class CRC16 {
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "$,BCT,170504171050,70300320,000070300320,2017-05-04#16:34:08,OK,0001,0011,0001,0,332C*";
        String hex = Integer.toHexString(s.length());
        System.out.println(hex);
        System.out.println(s.length() + hex.length());
        hex = Integer.toHexString(s.length() + hex.length() + 1);
        System.out.println(hex);
        System.out.println(getCRC16Hex("BCT,170504171050,70300320,000070300320,2017-05-04#16:34:08,OK,0001,0011,0001,0,"));
  }
    
    public static String getCRC16Hex(String string) {
        String str = Integer.toHexString(getCalCRC16(string.getBytes())).toUpperCase();
        if (str.length() > 4) {
            return str.substring(str.length() - 4);
        }
        else {
            return str;
        }
    }

    public static short getCalCRC16(byte[] data) {
        short crc = 0;
        for (char j = 0; j < data.length; j++) {
            for (char i = 0x80; i != 0; i >>= 1) {
                if ((crc & 0x8000) != 0) {
                    crc <<= 1;
                    crc ^= 0x1021;
                }
                else {
                    crc <<= 1;
                }
                if ((data[j] & i) != 0) {
                    crc ^= 0x1021;
                }
            }
        }
        return crc;
    }
}