package i9.defence.platform.dao;

import i9.defence.platform.model.ErrorRecord;

/** 
* 创建时间：2018年6月22日 上午8:50:16
* @author  lby
* @version  
* 
*/
public interface ErrorRecordDao {
	
	/**
	 * 新增错误记录
	 * @param errorRecord
	 */
	void insertErrorRecord(ErrorRecord errorRecord);
}
