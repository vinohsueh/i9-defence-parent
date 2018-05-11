package i9.defence.platform.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ConnectLogDao;
import i9.defence.platform.dao.mapper.ConnectLogMapper;
import i9.defence.platform.model.ConnectLog;

/** 
* 创建时间：2018年5月11日 下午5:01:59
* @author  lby
* @version  
* 
*/
@Repository
public class ConnectLogDaoImpl implements ConnectLogDao{

	@Autowired
	private ConnectLogMapper connectLogMapper;
	
	@Override
	public void add(ConnectLog connectLog) {
		connectLogMapper.insertSelective(connectLog);
	}

}
