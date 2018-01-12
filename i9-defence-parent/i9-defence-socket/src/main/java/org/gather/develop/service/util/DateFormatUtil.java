package org.gather.develop.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static Date parseDate(String string) {
        if (string == null || string.equals("")) return null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd#H:m:s");
        try {
            return dateFormat.parse(string);
        }
        catch (ParseException e) {
            return null;
        }
    }
    
    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd#HH:mm:ss");
        return dateFormat.format(date);
    }
}
