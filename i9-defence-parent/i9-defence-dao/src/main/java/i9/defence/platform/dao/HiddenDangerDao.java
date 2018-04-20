package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;
import i9.defence.platform.utils.PageBounds;

/**
 * 隐患提醒类型Dao
 * 
 * @ClassName: HiddenDangerDao
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月2日 下午3:22:00
 */
public interface HiddenDangerDao {

	/**
	 * 分页查询隐患提醒类型
	 * 
	 * @Title: selectByLimitPage
	 * @Description: TODO
	 * @param hiddenDangerExample
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBounds<HiddenDanger> selectByLimitPage(HiddenDangerExample hiddenDangerExample, int currectPage, int pageSize)
			throws Exception;

	/**
	 * 新增隐患提醒类型
	 * 
	 * @Title: insertHiddenDanger
	 * @Description: TODO
	 * @param hiddenDanger
	 * @throws Exception
	 */
	void insertHiddenDanger(HiddenDanger hiddenDanger) throws Exception;

	/**
	 * 更改隐患提醒
	 * 
	 * @Title: updateHiddenDanger
	 * @Description: TODO
	 * @param hiddenDanger
	 * @throws Exception
	 */
	void updateHiddenDanger(HiddenDanger hiddenDanger) throws Exception;

	/**
	 * 根据id获取隐患
	 * 
	 * @Title: getHiddenById
	 * @Description: TODO
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	HiddenDanger getHiddenById(Integer Id) throws Exception;

	/**
	 * 批量删除隐患
	 * 
	 * @Title: delHiddenDangers
	 * @Description: TODO
	 * @param ids
	 * @throws Exception
	 */
	void delHiddenDangers(List<Integer> ids) throws Exception;
	
	/**
	 * 全部类型
	 * @return
	 * @throws Exception
	 */
	List<HiddenDanger> selectAllHiddendanger() throws Exception;
	
	/**
	 * 查询有问题的设备
	 * @return
	 */
	List<String> selectErrorEquipmentIds();

}
