package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.Demo;
import i9.defence.platform.service.OldPlatformService;

/**
 * 创建时间：2018年5月7日 上午10:08:31
 * 
 * @author lby
 * @version
 * 
 */
@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private OldPlatformService testService;
	
	@RequestMapping("/select")
	public HashMap<String, Object> select() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Demo> list = testService.getListByDs1();
		result.put("list", list);
		return result;
	}
}
