package i9.defence.platform.datapush.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期转换工具类
 * 
 * @author R12
 * @date 2018年10月23日 09:45:39
 */
public class DateUtil {

    /**
     * 格式化 String => Date
     * 
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date parse(String dateStr) throws Exception {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * 格式化当前日期
     * 
     * @return
     */
    public static String format(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 获取日期增加天数
     * 
     * @param date
     * @param amount
     * @return
     */
    public static Date getDateZeroHour(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, amount);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String formatDateZeroHour(Date date, int amount) {
        Date t = getDateZeroHour(date, amount);
        return format(t);
    }
}
