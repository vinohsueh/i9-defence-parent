package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月19日 上午11:13:02 
* 
*/
public interface UpStreamDecodeService {

	/**
     * 添加解密数据
     * @param UpStreamDecode
     * @throws BusinessException
     */
	 void addUpStreamDecode(UpStreamDecode upStreamDecode) throws BusinessException;
	 
	 /**
	     * 分页查解密数据
	     * @param upStreamDecodeSearchDto
	     * @param currectPage
	     * @param pageSize
	     * @return
	     */
	    PageBounds<UpStreamDecode> selectByLimitPage(UpStreamDecodeSearchDto upStreamDecodeSearchDto) throws BusinessException;
	    /**
	     * 删除解密数据
	     * @param kid
	     * @throws Exception
	     */
	    void deleteUpStreamDecode(List<Integer> ids) throws BusinessException;

        void saveUpStreamDecode(String jsonStr) throws Exception;
        
        /**
         * 跟新设备的状态
         * @param deviceId
         * @param status
         */
		void updateEquipmentStatus(String deviceId, int status);
		
		/**
		 * 添加链掉线记录
		 * @param connectLog
		 */
		void insertConnectRecord(ConnectLog connectLog);
}
 