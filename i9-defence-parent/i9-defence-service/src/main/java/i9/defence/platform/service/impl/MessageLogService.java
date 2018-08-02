package i9.defence.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.MessageLogDao;
import i9.defence.platform.model.MessageLog;
import i9.defence.platform.utils.BusinessException;

/**
 * 短信日志ServiceImpl
 * @ClassName: MessageLogService 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月1日 上午9:58:11
 */
@Service
public class MessageLogService implements i9.defence.platform.service.MessageLogService {

	@Autowired
	private MessageLogDao MessageLogDao;
	
	@Override
	public void insert(MessageLog messageLog) throws BusinessException {
		try {
			MessageLogDao.insert(messageLog);
		} catch (Exception e) {
			throw new BusinessException("新增短信日志失败", e.getMessage());
		}
	}

}
