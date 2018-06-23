package i9.defence.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i9.defence.platform.dao.ErrorRecordDao;
import i9.defence.platform.model.ErrorRecord;
import i9.defence.platform.service.ErrorRecordService;
import i9.defence.platform.utils.BusinessException;

/** 
* 创建时间：2018年6月22日 上午8:55:46
* @author  lby
* @version  
* 
*/
@Service
public class ErrorRecordServiceImpl implements ErrorRecordService{
	
	@Autowired
	private ErrorRecordDao errorRecordDao;
	
	@Override
	public void insertErrorRecord(ErrorRecord errorRecord) throws BusinessException {
		try {
			errorRecordDao.insertErrorRecord(errorRecord);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
