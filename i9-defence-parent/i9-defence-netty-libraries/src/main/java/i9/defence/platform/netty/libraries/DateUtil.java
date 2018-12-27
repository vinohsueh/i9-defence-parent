package i9.defence.platform.netty.libraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String format(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'#'HH:mm:ss");
        return dateFormat.format(date);
    }
}
