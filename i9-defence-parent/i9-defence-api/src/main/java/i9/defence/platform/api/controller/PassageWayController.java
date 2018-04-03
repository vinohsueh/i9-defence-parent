package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.model.Passageway;
import i9.defence.platform.service.passagewayService;

/**
 * 通道controller
 * 
 * @ClassName: PassageWayController
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月2日 下午5:11:32
 */
@RestController
@RequestMapping("/passageWay")
public class PassageWayController {

	@Autowired
	private passagewayService passagewayService;

	/**
	 * 根据设备id查询通道
	 * 
	 * @Title: selectPassagewaysByEquipId
	 * @Description: TODO
	 * @param Id
	 * @return
	 */
	@RequestMapping("/selectPassagewaysByEquipId")
	public HashMap<String, Object> selectPassagewaysByEquipId(Integer Id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Passageway> list = passagewayService.selectPassagewaysByEquipId(Id);
		result.put("data", list);
		return result;
	}

	/**
	 * 新增通道
	 * 
	 * @Title: addPassageway
	 * @Description: TODO
	 * @param passageway
	 * @return
	 */
	@RequestMapping("/addPassageway")
	public HashMap<String, Object> addPassageway(Passageway passageway) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		passagewayService.addPassageway(passageway);
		return result;
	}
}
