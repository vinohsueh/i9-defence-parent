package i9.defence.platform.mq;

import i9.defence.platform.utils.DateUtils;

import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void testDateUtils() {
        Date date = new Date();
        String dateStr = DateUtils.formatDate(date);
        System.out.println(dateStr);
        date = DateUtils.parseDate(dateStr);
        System.out.println(date);
    }
}
