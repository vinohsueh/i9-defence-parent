package i9.defence.platform.dao;

import i9.defence.platform.model.MessageLog;

/**
 * 短信日志
 * @ClassName: MessageLogDao 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年7月31日 上午11:36:03
 */
public interface MessageLogDao {
	
	/**
	 * 新增短信日志
	* @Title: insert 
	* @Description: TODO
	* @param messageLog
	* @throws Exception
	 */
	void insert(MessageLog messageLog) throws Exception;

}
