package i9.defence.platform.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.MessageLogDao;
import i9.defence.platform.dao.mapper.MessageLogMapper;
import i9.defence.platform.model.MessageLog;

/**
 * 短信日志impl
 * @ClassName: MessageLogDaoImpl 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年7月31日 上午11:38:09
 */
@Repository
public class MessageLogDaoImpl implements MessageLogDao {

	@Autowired
	private MessageLogMapper MessageLogMapper;
	
	@Override
	public void insert(MessageLog messageLog) throws Exception {
		MessageLogMapper.insert(messageLog);
	}

}
