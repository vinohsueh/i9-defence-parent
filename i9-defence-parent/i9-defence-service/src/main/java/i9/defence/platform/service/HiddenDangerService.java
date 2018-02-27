package i9.defence.platform.service;

import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 隐患类型提醒Service
* @ClassName: HiddenDangerService 
* @Description: TODO
* @author luobo
* @date 2018年1月10日 上午11:21:59 
*
 */
public interface HiddenDangerService {

	/**
	 * 增加隐患提醒类型
	 * @param HiddenDanger
	 * @throws Exception
	 */
	void addHiddenDanger(HiddenDanger hiddenDanger) throws BusinessException;
	
	/**
	 * 分页查询隐患提醒类型
	 * @param HiddenDangerDto
	 * @param currectPage
	 * @param pageSize
	 * @throws Excepiton
	 */
	PageBounds<HiddenDanger> selectByLimitPage(HiddenDangerDto hiddenDangerDto)throws BusinessException;
	
	/**
	 * 根据id查询
	* @Title: getHiddenDanger 
	* @Description: TODO
	* @param id
	* @return HiddenDanger
	* @throws BusinessException
	 */
	HiddenDanger getHiddenDanger(int id)throws BusinessException;
	
	
	/**
	* 更新
	* @Title: hiddenDanger 
	* @Description: TODO
	* @param hiddenDanger
	* @throws Exception
	 */
	void  updateHiddenDanger(HiddenDanger hiddenDanger) throws Exception; 
}
