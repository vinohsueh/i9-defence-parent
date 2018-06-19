package i9.defence.platform.socket.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static final String EMPTY = "";

    public static String convertArgsToStr(String... args) {
        if (args.length == 0) {
            return EMPTY;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : args) {
            stringBuffer.append(",").append(s);
        }
        return stringBuffer.substring(1);
    }

    public static String DateNowStr() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ii:ss");
        return dateFormat.format(new Date());
    }
}
