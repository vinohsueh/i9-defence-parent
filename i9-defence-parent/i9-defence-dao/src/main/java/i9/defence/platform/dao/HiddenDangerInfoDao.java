package i9.defence.platform.dao;

import i9.defence.platform.dao.vo.HiddenDangerInfoDto;
import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.utils.PageBounds;

/**
 * 隐患提醒类型Dao
* @ClassName: HiddenDangerInfoDao 
* @Description: TODO
* @author luobo
* @date 2018年1月9日 下午4:42:53 
*
 */
public interface HiddenDangerInfoDao {
	
	/**
	 * 增加隐患提醒
	* @Title: addHiddenDangerInfo  
	* @Description: TODO
	* @param hiddenDangerInfo
	* @throws Exception 
	* @return void 
	* @throws
	 */
	void addHiddenDangerInfo(HiddenDangerInfo hiddenDangerInfo)throws Exception;
	
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
	PageBounds<HiddenDangerInfo>  selectByLimitPage(HiddenDangerInfoDto hiddenDangerInfoInfoDto,
			 int currectPage, int pageSize)throws Exception;
}
