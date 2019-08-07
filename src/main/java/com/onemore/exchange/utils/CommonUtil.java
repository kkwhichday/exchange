package com.onemore.exchange.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微信小程序通用工具類
 * @author HuangKailie
 * @createTime 2017-11-13 20:18:05
 */
public class CommonUtil {

    /**
     * 判斷字符是否為中文
     * @param c 字符
     * @return boolean 是否為中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判斷字符串是否亂碼
     * @param strName 字符串
     * @return boolean 是否為亂碼
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成6位數驗證碼
     * @return int 6位數驗證碼
     */
    public static int getmsgCode() {
        return (int)(Math.random() * 900000) + 100000;
    }

    /**
     * 解決@RequestParam傳值中文亂碼
     * @param str 需要解決中文亂碼的字符串
     * @return String 格式化後的字符串
     */
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 將經緯度轉化為弧度
     * @param f 經緯度
     * @return float 弧度
     */
    public static Double rad(Double f) {
        return f * Math.PI / 180.0;
    }

    /**
     * 基於余弦定理求兩經緯度距離
     * @param lon1 位置壹的經度
     * @param lat1 位置壹的緯度
     * @param lon2 位置二的經度
     * @param lat2 位置二的緯度
     * @return float 距離，單位km
     */
    public static double latitudeLongitudeDist(Double lon1, Double lat1, Double lon2, Double lat2) {
        final double EARTH_RADIUS = 6378137; // 赤道半徑(單位m)
        /*
         * 將經緯度轉換成弧度
         */
        Double radLon1 = rad(lon1);
        Double radLat1 = rad(lat1);
        Double radLon2 = rad(lon2);
        Double radLat2 = rad(lat2);
        /*
         * 判斷方向
         */
        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));

        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d)
                / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double dist = theta * EARTH_RADIUS;
        return dist / 1000;
    }
}