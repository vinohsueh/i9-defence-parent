package i9.defence.platform.datapush.utils;

import java.util.UUID;

public class StringHelper {

    public static String randomUUIDStr() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
