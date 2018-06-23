package i9.defence.platform.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ErrorRecordDao;
import i9.defence.platform.dao.mapper.ErrorRecordMapper;
import i9.defence.platform.model.ErrorRecord;

/** 
* 创建时间：2018年6月22日 上午8:52:45
* @author  lby
* @version  
* 
*/
@Repository
public class ErrorRecordDaoImpl implements ErrorRecordDao{
	
	@Autowired
	private ErrorRecordMapper errorRecordMapper;
	
	@Override
	public void insertErrorRecord(ErrorRecord errorRecord) {
		errorRecordMapper.insertSelective(errorRecord);
	}

}
