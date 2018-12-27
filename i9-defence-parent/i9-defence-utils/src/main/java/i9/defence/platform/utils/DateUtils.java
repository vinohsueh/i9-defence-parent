package i9.defence.platform.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date parseDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }

    public static Date parseDate(String pStr, String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(pStr);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }

    public static String DateNowStr() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
