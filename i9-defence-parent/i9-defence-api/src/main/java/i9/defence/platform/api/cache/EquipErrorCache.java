package i9.defence.platform.api.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import i9.defence.platform.model.EquipmentFault;
import i9.defence.platform.service.EquipmentFaultService;


/** 
 * 设备故障缓存
* 创建时间：2018年3月29日 上午10:19:28
* @author  lby
* @version  
* 
*/
@Component("equipErrorCache")
public class EquipErrorCache {
	
	@Autowired
	private EquipmentFaultService equipmentFaultService;
	
	public static Map<String,String> EQUIPMENT_ERROR_CACHE = new HashMap<String,String>();
	
	public void initData(){
		//清除缓存
		EquipErrorCache.EQUIPMENT_ERROR_CACHE.clear();
		List<EquipmentFault> list = equipmentFaultService.selectAllFaults();
		
		//将故障存在缓存中  故障编号作为key
		for (EquipmentFault equipmentFault : list) {
			//EQUIPMENT_ERROR_CACHE.put(equipmentFault.get, value)
		}
		System.out.println("------------------------------缓存启动");
	}
	
}
