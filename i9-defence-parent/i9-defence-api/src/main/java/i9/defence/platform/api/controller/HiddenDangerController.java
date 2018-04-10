package i9.defence.platform.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.PageListDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.HiddenDangerExample;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.utils.PageBounds;

/**
 * 隐患提醒类型Controller
 * 
 * @ClassName: HiddenDangerController
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月10日 上午11:47:26
 */
@RestController
@RequestMapping("/hiddendanger")
public class HiddenDangerController {

	@Autowired
	private HiddenDangerService hiddernDangerService;

	/**
	 * 分页查询隐患提醒类型
	 * 
	 * @param managerSearchDto
	 * @param currectPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/pageHiddendanger")
	public HashMap<String, Object> pageHiddendanger(@RequestBody PageListDto pageList) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HiddenDangerExample hiddenDangerExample = new HiddenDangerExample(); 
		PageBounds<HiddenDanger> pageBounds = hiddernDangerService.selectByLimitPage(hiddenDangerExample, pageList.getCurrentPage(), pageList.getPageSize()); 
		result.put("data", pageBounds); 
		return result;
	}
 
	/**
	 * 添加隐患提醒类型
	 * 
	 */
	@RequestMapping("/addHiddendanger")
	public HashMap<String, Object> addHiddendanger(@RequestBody HiddenDanger hiddenDanger) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		hiddernDangerService.insertHiddenDanger(hiddenDanger);
		return result;
	}
	
	/**
	 * 根据隐患id获得隐患
	* @Title: getHiddendangerById 
	* @Description: TODO
	* @param id
	* @return
	 */
	@RequestMapping("/getHiddendangerById")
	public HashMap<String, Object> getHiddendangerById(@RequestBody Integer id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HiddenDanger hiddenDanger = hiddernDangerService.getHiddenById(id);
		result.put("data", hiddenDanger);
		return result;
	}
}
