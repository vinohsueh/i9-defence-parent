package i9.defence.platform.api.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import i9.defence.platform.service.EquipmentFaultService;


/** 
 * 设备故障缓存
* 创建时间：2018年3月29日 上午10:19:28
* @author  lby
* @version  
* 
*/
@Configuration
public class EquipErrorCache {
	
	@Autowired
	private EquipmentFaultService equipmentFaultService;
	
	public static Map<String,Map<String,String>> EQUIPMENT_ERROR_CACHE = new HashMap<String,Map<String,String>>();
	
	public void show(){
		System.out.println("------------------------------缓存启动");
	}
	
}
