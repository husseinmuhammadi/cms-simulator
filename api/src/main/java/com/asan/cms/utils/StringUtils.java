package com.asan.cms.utils;

import javax.xml.bind.DatatypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    public static byte[] hexStringToByte(String hexString)
    {
        return DatatypeConverter.parseHexBinary(hexString);
    }

    public static String byteToHexString(byte[] bytes)
        {
            return DatatypeConverter.printHexBinary(bytes);
        }

    public static String ifNull(String str, String ifNullValue) {
        if(str != null)
            return str;
        else return ifNullValue;
    }

    /**
     *
     * @param date
     * @return format yyyyMMddHHmmss
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     *
     * @param date
     * @return format yyyyMMddHHmmss
     */
    public static String formatReadableDate(Date date) {
        if(date == null) return "null";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     *
     * @param dateStr format: yyyyMMddHHmmss
     * @return
     */
    public static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.parse(dateStr);
    }


    public static String LeftPadRRN(long rrn) {
        return String.format("%012d", rrn);
    }

}
