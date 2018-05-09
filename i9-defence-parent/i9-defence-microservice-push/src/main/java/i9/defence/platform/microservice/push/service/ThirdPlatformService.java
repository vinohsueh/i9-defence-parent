package i9.defence.platform.microservice.push.service;


/**
 * 创建时间：2018年5月7日 上午10:09:26
 * 
 * @author lby
 * @version
 */
public interface ThirdPlatformService {

    /**
     * 保存故障
     * 
     * @param text
     */
    void saveAlertOrigin(String text) throws Exception;
}
