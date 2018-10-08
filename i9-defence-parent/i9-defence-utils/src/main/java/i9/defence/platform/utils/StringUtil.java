package i9.defence.platform.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 创建时间：2017年11月16日 下午5:07:21
 * 
 * @author lby
 * @version
 * 
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
        }
    }

    public static String MD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdm.format(date);
    }

    public static String dateToStringByRep(Date date, String rep) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdm = new SimpleDateFormat(rep);
        return sdm.format(date);
    }

    /**
     * 日期转换
     * 
     * @param date
     * @return
     */
    public static String dateToStringWithoutTime(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
        return sdm.format(date);
    }

    /**
     * 时间转日期
     * 
     * @param endDateString
     * @param string
     * @return
     */
    public static Date stringToDateByRep(String endDateString, String rep) {
        if (StringUtils.isBlank(endDateString)) {
            return null;
        }
        SimpleDateFormat sdm = new SimpleDateFormat(rep);
        try {
            return sdm.parse(endDateString);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 日期转换
     * 
     * @param date
     * @return
     * @throws ParseException 
     */
    public static Date StringToDateS(String date) throws ParseException {
        if (org.apache.commons.lang.StringUtils.isNotBlank(date)) {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdm.parse(date);
        } else {
            return null;
        }
    }
    
    
    
    

    /**
     * 获取设备唯一id
     * 
     * @param systemId
     *            设备编号
     * @param loop
     *            回路号
     * @param address
     *            设备地址
     * @return
     */
    public static String getDeviceId(String systemId, int loop, String address) {
        StringBuffer str = new StringBuffer();
        str.append(systemId).append(EncryptUtils.bytesToHexString(EncryptUtils.intToBytes(loop))).append(address);
        return str.toString();
    }
}
