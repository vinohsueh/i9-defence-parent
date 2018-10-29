package i9.defence.platform.api.comparator;

import java.util.Comparator;

/**
 * 创建时间：2018年4月20日 上午11:14:00
 * 
 * @author lby
 * @version
 * 
 */
public class MonthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] array1 = o1.split("-");
        String[] array2 = o2.split("-");
        // 月份排序 ["2018-1","2018-2"]
        int year1 = Integer.valueOf(array1[0]);
        int month1 = Integer.valueOf(array1[1]);
        int year2 = Integer.valueOf(array2[0]);
        int month2 = Integer.valueOf(array2[1]);
        if (year1 < year2) {
            return -1;
        } else if (year1 > year2) {
            return 1;
        } else {
            if (month1 < month2) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
