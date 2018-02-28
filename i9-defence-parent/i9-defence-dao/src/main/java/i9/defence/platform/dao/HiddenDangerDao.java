package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * 隐患提醒类型Dao
* @ClassName: HiddenDangerDao 
* @Description: TODO
* @author luobo
* @date 2018年1月10日 上午10:30:15 
*
 */
public interface HiddenDangerDao {
	
	/**
	 * 增加隐患提醒类型
	 * @param HiddenDanger
	 * @throws Exception
	 */
	void addHiddenDanger(HiddenDanger hiddenDanger) throws Exception;
	
	/**
	 * 分页查询隐患提醒类型
	 * @param HiddenDangerDto
	 * @param currectPage
	 * @param pageSize
	 * @throws Excepiton
	 */
	PageBounds<HiddenDanger> selectByLimitPage(HiddenDangerDto hiddenDangerDto,
			 int currectPage, int pageSize)throws Exception;
	/**
	 * 根据id查询
	* @Title: getHiddenDanger 
	* @Description: TODO
	* @param id
	* @return HiddenDanger
	* @throws BusinessException
	 */
	HiddenDanger getHiddenDanger(int id)throws Exception; 
	
	/**
	* 更新
	* @Title: hiddenDanger 
	* @Description: TODO
	* @param hiddenDanger
	* @throws Exception
	 */
	void  updateHiddenDanger(HiddenDanger hiddenDanger) throws Exception;
	
	/**
	 * 获取所有隐患类型
	* @Title: getAllHidenDangers 
	* @Description: TODO
	* @return
	* @throws Exception
	 */
	List<HiddenDanger> getAllHidenDangers() throws Exception;
	
}
