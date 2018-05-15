package i9.defence.platform.dao;

import i9.defence.platform.model.ConnectLog;

/** 
* 创建时间：2018年5月11日 下午5:01:00
* @author  lby
* @version  
* 
*/
public interface ConnectLogDao {
	
	/**
	 * 添加离线掉线记录
	 * @param connectLog
	 */
    void add(ConnectLog connectLog);
}
