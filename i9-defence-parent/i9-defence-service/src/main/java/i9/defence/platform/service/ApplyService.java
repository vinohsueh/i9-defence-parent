package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ApplyExample;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 删除项目，设备申请表Service
 * @ClassName: ApplyService 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月25日 上午10:35:17
 */
public interface ApplyService {
	
	/**
	 * 分页查询申请表
	* @Title: selectByLimitPage 
	* @Description: TODO
	* @param applyExample
	* @param currectPage
	* @param pageSize
	* @return
	* @throws Exception
	 */
	PageBounds<Apply>  selectByLimitPage(ApplyExample applyExample, int currectPage, int pageSize ,Integer state) throws BusinessException;

	/**
	 * 删除申请
	* @Title: delApply 
	* @Description: TODO
	* @param ids
	* @throws BusinessException
	 */
	void delApply(List<Integer> ids) throws BusinessException;
	
	/**
	 * 根据id查询Apply
	* @Title: getApplyById 
	* @Description: TODO
	* @param id
	* @return
	* @throws Exception
	 */
	Apply getApplyById(int id)throws BusinessException;
	
	 
	/**
	* 查询部分状态申请列表
	* @Title: selectPartState 
	* @Description: TODO
	* @param state
	* @return
	* @throws BusinessException
	 */
	List<Apply> selectPartState(Integer state) throws BusinessException;
 
}
