package i9.defence.platform.service;

import i9.defence.platform.model.ErrorRecord;
import i9.defence.platform.utils.BusinessException;

/** 
* 创建时间：2018年6月22日 上午8:55:12
* @author  lby
* @version  
* 
*/
public interface ErrorRecordService {

	/**
	 * 新增错误记录
	 * @param errorRecord
	 * @throws BusinessException
	 */
	void insertErrorRecord(ErrorRecord errorRecord) throws BusinessException;
}
