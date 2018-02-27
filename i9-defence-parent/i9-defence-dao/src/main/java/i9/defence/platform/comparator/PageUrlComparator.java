package i9.defence.platform.comparator;

import i9.defence.platform.model.PageUrl;

import java.util.Comparator;

/** 
 * 创建时间：2018年2月8日 下午2:13:35
 * @author  lby
 * @version  
 * 
 */
public class PageUrlComparator implements Comparator<PageUrl>{

    @Override
    public int compare(PageUrl o1, PageUrl o2) {
        if (o1.getOrderNumber() <= o2.getOrderNumber()) {
            return -1;
        }else{
            return 1;
        }
    }

}
