package i9.defence.platform.datapush.utils;

import java.util.UUID;

/**
 * 字符串工具类
 * 
 * @author jiangtao
 */
public class StringHelper {

    /**
     * 随机uuid字符串
     * 
     * @return
     */
    public static String randomUUIDStr() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
