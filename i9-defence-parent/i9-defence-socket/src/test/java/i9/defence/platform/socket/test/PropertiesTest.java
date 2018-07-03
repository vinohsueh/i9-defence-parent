package i9.defence.platform.socket.test;

import java.util.Properties;

public class PropertiesTest {
    
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("a", "username");
        properties.setProperty("b", "password");
        System.out.println(resolvePlaceHolder("${a}你好,${b}密码是这个么？", properties));
    }

    /** 
     * 解析占位符 
     * @param property 
     * @param properties 
     */ 
    public static String resolvePlaceHolder(String formatStr, Properties properties) {
        StringBuffer buff = new StringBuffer();
        char[] chars = formatStr.toCharArray();
        for (int pos = 0; pos < chars.length; pos++) {
            if (chars[pos] == '$') {
                if (chars[pos + 1] == '{') {
                    String systemPropertyName = "";
                    int x = pos + 2;
                    for (; x < chars.length && chars[x] != '}'; x++) {
                        systemPropertyName += chars[x];
                        if (x == chars.length - 1) {
                            throw new IllegalArgumentException("unmatched placeholder start [" + formatStr + "]");
                        }
                    }
                    String systemProperty = extractFromSystem(systemPropertyName, properties);
                    buff.append(systemProperty == null ? "" : systemProperty);
                    pos = x + 1;
                    if (pos >= chars.length) {
                        break;
                    }
                }
            }
            buff.append(chars[pos]);
        }
        String rtn = buff.toString();
        return isEmpty(rtn) ? null : rtn;
    }

    private static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    private static String extractFromSystem(String systemPropertyName, Properties properties) {
        return properties.getProperty(systemPropertyName);
    }
}
