package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.Demo;

/** 
* 创建时间：2018年5月7日 上午10:09:26
* @author  lby
* @version  
* 
*/
public interface OldPlatformService {
    
    List<Demo> getListByDs1();
    
    /**
     * 保存故障
     * @param text
     */
    void saveAlertOrigin(String text) throws Exception;
}
