package i9.defence.platform.api.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;


/** 
 * 设备故障缓存
* 创建时间：2018年3月29日 上午10:19:28
* @author  lby
* @version  
* 
*/
@Configuration
public class EquipErrorCache {
	
	public static Map<String,Map<String,String>> EQUIPMENT_ERROR_CACHE = new HashMap<String,Map<String,String>>();
	
	public void show(){
		System.out.println("------------------------------缓存启动");
	}
	
}
