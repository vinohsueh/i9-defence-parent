package i9.defence.platform.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i9.defence.platform.dao.vo.EquipmentLoopDto;
import i9.defence.platform.model.EquipmentLoop;
import i9.defence.platform.service.EquipmentLoopService;
import i9.defence.platform.utils.PageBounds;

/**
 * 设备回路Controller
 * @ClassName: EquipmentLoopController 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年5月25日 下午4:48:23
 */
@RestController
@RequestMapping("/equipmentLoop")
public class EquipmentLoopController {
	
	@Autowired
	private EquipmentLoopService equipmentLoopService;
	
	@RequestMapping("/pageEquipmentLoop")
	public HashMap<String, Object> pageEquipmentLoop(@RequestBody EquipmentLoopDto equipmentSearchDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		PageBounds<EquipmentLoop> pageBounds = equipmentLoopService.selectByLimitPage(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize());
		result.put("data", pageBounds);
		return result;
	}
}
