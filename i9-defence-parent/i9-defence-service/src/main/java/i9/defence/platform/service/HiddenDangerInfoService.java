package i9.defence.platform.service;

import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.model.HiddenDangerInfoExample;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 隐患提醒类型Service
 * @ClassName: HiddenDangerInfoService 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月10日 下午4:52:56
 */
public interface HiddenDangerInfoService {

	/**
	 * 增加隐患提醒
	* @Title: addHiddenDangerInfo  
	* @Description: TODO
	* @param hiddenDangerInfo
	* @throws Exception 
	* @return void 
	* @throws
	 */
	void addHiddenDangerInfo(HiddenDangerInfo hiddenDangerInfo)throws BusinessException;
	
	/**
	 * 分页查询隐患提醒
	* @Title: selectByLimitPage  
	* @Description: TODO
	* @param  hiddenDangerInfoInfoDto
	* @param  currectPage
	* @param  pageSize
	* @param @return
	* @throws Exception 
	* @return PageBounds<HiddenDangerInfo> 
	 */
	PageBounds<HiddenDangerInfo>  selectByLimitPage(HiddenDangerInfoExample hiddenDangerInfoExample, int currectPage,
			int pageSize)throws BusinessException;
	
	/**
	 * 根据id查询
	* @Title: selectById 
	* @Description: TODO
	* @param id
	* @return
	* @throws Exception
	 */
	HiddenDangerInfo  selectById(int id) throws BusinessException;

 
}
