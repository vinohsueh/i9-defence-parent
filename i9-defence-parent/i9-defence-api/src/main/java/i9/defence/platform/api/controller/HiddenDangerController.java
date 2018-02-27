package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.utils.PageBounds;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 隐患提醒类型Controller
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
     * @param managerSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
	@RequestMapping("/pageHiddendanger")
	public HashMap<String, Object> pageHiddendanger(@RequestBody HiddenDangerDto hiddenDangerDto){
	HashMap<String,Object> result = new HashMap<String, Object>();
	PageBounds<HiddenDanger> pageBounds = hiddernDangerService.selectByLimitPage(hiddenDangerDto);
	result.put("data",pageBounds);
	return result;
	}
	
	/**
	 * 添加隐患提醒类型
	 * 
	 */
	 @RequestMapping("/addHiddendanger")
	 public HashMap<String, Object> addHiddendanger(@RequestBody HiddenDanger hiddenDanger){
	 HashMap<String,Object> result = new HashMap<String, Object>();
	 hiddernDangerService.addHiddenDanger(hiddenDanger);
	 return result;
	 }
	
	 /**
	  * 根据id查询隐患提醒类型
	  */
	  @RequestMapping("/getHiddendanger")
	  public HashMap<String, Object> getHiddendanger(@RequestBody Integer hiddenDangerId){
	  HashMap<String,Object> result = new HashMap<String, Object>();
	  HiddenDanger hiddenDanger2 = hiddernDangerService.getHiddenDanger(hiddenDangerId);
	  result.put("data", hiddenDanger2);
	  return result;
	  }
}
