package i9.defence.platform.datapush.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * 
 * @author R12
 * @date 2018年10月23日 09:45:39
 */
public class DateUtil {

    public static Date parse(String dateStr) throws Exception {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            return new Date();
        }
    }
}
