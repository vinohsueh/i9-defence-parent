package i9.defence.platform.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.api.components.PassagewayInfoComponent;
import i9.defence.platform.model.HiddenDanger;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.service.PassagewayService;

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
	private PassagewayService passagewayService;
	
	@Autowired
	private HiddenDangerService hiddenDangerService;
	
	
	/*@RequestMapping("/selectPassagewaysByEquipId")
	public HashMap<String, Object> selectPassagewaysByEquipId(@RequestBody Equipment equipment) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Passageway> list = passagewayService.selectPassagewaysByEquipId(equipment.getId());
		JSONArray jsonArray = new JSONArray();
		for (Passageway passageway : list) {
			JSONObject jsonObject = new PassagewayInfoComponent().setPassageway(passageway).build();
			jsonArray.add(jsonObject);
		}
		List<HiddenDanger> hiddenDangers = hiddernDangerService.selectAllHiddendanger();
		result.put("dangers", hiddenDangers);
		result.put("data", jsonArray);
		return result;
	}*/
	
	/**
	 * 根据设备类型查询通道
	 * @Title: selectPassagewaysByEquipId
	 * @Description: TODO
	 * @param Id
	 * @return
	 */
	@RequestMapping("/selectPassagewaysByCategoryId")
	public HashMap<String, Object> selectPassagewaysByEquipId(@RequestBody Integer id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Passageway> list = passagewayService.selectPassagewaysByCategoryId(id);
		JSONArray jsonArray = new JSONArray();
		for (Passageway passageway : list) {
			JSONObject jsonObject = new PassagewayInfoComponent().setPassageway(passageway).build();
			jsonArray.add(jsonObject);
		}
		List<HiddenDanger> hiddenDangers = hiddenDangerService.selectAllHiddendanger();
		result.put("dangers", hiddenDangers);
		result.put("data", jsonArray);
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
	public HashMap<String, Object> addPassageway(@RequestBody Passageway passageway) {
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		passagewayService.addPassageway(passageway);
		return result;
	}
	
	/**
	 * 
	 * @Title: addPassageway
	 * @Description: TODO
	 * @param passageway
	 * @return
	 */ 
	@RequestMapping("/delPassageway")
	public HashMap<String, Object> delPassageway(@RequestBody Passageway passageway) {
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		passagewayService.delPassageway(passageway);
		return result;
	}
	 
}
