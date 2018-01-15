package i9.defence.platform.socket.util;

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
}
