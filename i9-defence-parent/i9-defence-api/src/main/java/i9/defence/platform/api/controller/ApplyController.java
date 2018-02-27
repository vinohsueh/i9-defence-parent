package i9.defence.platform.api.controller;

import i9.defence.platform.dao.vo.PageListDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ApplyExample;
import i9.defence.platform.service.ApplyService;
import i9.defence.platform.utils.PageBounds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 删除设备、项目申请表Controller
 * @ClassName: ApplyController 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月25日 上午10:39:41
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
	
	/**
	 * 分页展示申请列表
	* @Title: getApply 
	* @Description: TODO
	* @param pageListDto
	* @return
	 */
	@RequestMapping("/pageApply")
	public HashMap<String, Object> getApply(@RequestBody PageListDto pageListDto){
        HashMap<String, Object> result = new HashMap<String, Object>();
        ApplyExample example= new ApplyExample();
        PageBounds<Apply> pageBounds = applyService.selectByLimitPage(example, pageListDto.getCurrentPage(), pageListDto.getPageSize(),pageListDto.getState());
        result.put("data",pageBounds);
        return result;
	}
	
	@RequestMapping("/delApply")
	public HashMap<String, Object> delApply(@RequestBody Integer [] ids){
        HashMap<String, Object> result = new HashMap<String, Object>();
        applyService.delApply(Arrays.asList(ids));
        return result;
	}

	@RequestMapping("/selectPartState")
	public HashMap<String, Object> selectPartState(Integer state){
		 HashMap<String, Object> result = new HashMap<String, Object>();
		 List<Apply> list = applyService.selectPartState(state);
		 result.put("data", list);
		 return result;
	}
}
