package i9.defence.platform.microservice.mq;

import i9.defence.platform.utils.DateUtils;

import java.util.Date;

import org.junit.Test;

/**
 * 日期工具包测试类
 *  
 * @author: jiangtao
 * @date: 2018-12-27 21:21:09
 */
public class DateUtilsTest {

    @Test
    public void testDateUtils() {
        Date date = new Date();
        String dateStr = DateUtils.formatDate(date);
        System.out.println(dateStr);
        date = DateUtils.parseDate(dateStr);
        System.out.println(date);
    }

    @Test
    public void testCharAt() {
        String s = "0000001101";
        for (int i = 1; i <= 3; i++) {
            char c = s.charAt(s.length() - i);
            System.err.println(c == '1');
        }
    }
}
