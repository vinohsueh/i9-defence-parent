package i9.defence.platform.service;

import i9.defence.platform.model.MessageLog;
import i9.defence.platform.utils.BusinessException;

/**
 * 短信日志Service
 * @ClassName: MessageLogService 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月1日 上午9:57:11
 */
public interface MessageLogService {
	
	/**
	 * 新增短信日志
	* @Title: insert 
	* @Description: TODO
	* @param messageLog
	* @throws BusinessException
	 */
	void insert(MessageLog messageLog) throws BusinessException;
}
