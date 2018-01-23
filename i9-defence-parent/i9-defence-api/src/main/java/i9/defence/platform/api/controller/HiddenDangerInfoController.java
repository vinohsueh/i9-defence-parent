package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.HiddenDangerInfoDto;
import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.service.HiddenDangerInfoService;
import i9.defence.platform.utils.PageBounds;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 隐患提醒类型具体信息Controller
 * @ClassName: HiddenDangerInfoController 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月10日 下午5:04:09
 */
@Controller
@RequestMapping("/hiddenDangerInfo")
public class HiddenDangerInfoController {

	@Autowired 
	private HiddenDangerInfoService hiddenDangerInfoService;
	
	/**
	 * 分页展示隐患提醒类型具体信息
	* @Title: pageHiddenDangerInfo  
	* @Description: TODO
	* @param:@param hiddenDangerInfoDto
	* @param:@return
	* @return:HashMap<String,Object> 
	* throws
	 */
	@ResponseBody
	@RequestMapping("/pageHiddenDangerInfo")
	public HashMap<String, Object> pageHiddenDangerInfo(HiddenDangerInfoDto hiddenDangerInfoDto){
	HashMap<String, Object> result = new HashMap<String, Object>();
	PageBounds<HiddenDangerInfo> pageBounds = hiddenDangerInfoService.selectByLimitPage(hiddenDangerInfoDto);
	result.put("data", pageBounds);
	return result;
	}
	
	/**
	 * 添加展示隐患提醒类型具体信息
	 * 
	 */
	@RequestMapping("/addHiddenDangerInfo")
	public HashMap<String, Object> addHiddenDangerInfo(HiddenDangerInfo hiddenDangerInfo){
	HashMap<String, Object> result = new HashMap<String, Object>();
	hiddenDangerInfoService.addHiddenDangerInfo(hiddenDangerInfo);
	return result;
	}
}
