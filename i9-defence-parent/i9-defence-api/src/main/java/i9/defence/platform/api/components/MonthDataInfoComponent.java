package i9.defence.platform.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.vo.MonthData;

/** 
* 创建时间：2018年4月14日 上午11:05:48
* @author  lby
* @version  
* 
*/
public class MonthDataInfoComponent {
	private List<MonthData> warningData;
	
	private List<MonthData> hiddenData;
	
	public MonthDataInfoComponent setWarningData(List<MonthData> warningData) {
		this.warningData=warningData;
		return this;
	}
	
	public MonthDataInfoComponent setHiddenData(List<MonthData> hiddenData) {
		this.hiddenData=hiddenData;
		return this;
	}
	
	public JSONObject build(){
		JSONObject jsonObject = new JSONObject();
		
		//报警map
		Map<String, Integer> warningDataMap = new HashMap<String, Integer>();
		List<Integer> warningCount = new ArrayList<>();
		//隐患map
		Map<String, Integer> hiddenDataMap = new HashMap<String, Integer>();
		List<Integer> hiddenCount = new ArrayList<>();
		//将所有的月份加在一起
		Set<String> set = new HashSet<>();
		for (MonthData monthData : warningData) {
			set.add(monthData.getMonth());
			warningDataMap.put(monthData.getMonth(), monthData.getCount());
		}
		for (MonthData monthData : hiddenData) {
			set.add(monthData.getMonth());
			hiddenDataMap.put(monthData.getMonth(), monthData.getCount());
		}
		
		
		
		jsonObject.put("months", set);
		
		for (String string : set) {
			//添加报警柱状图数据
			if (warningDataMap.containsKey(string)){
				warningCount.add(warningDataMap.get(string));
			}else{
				warningCount.add(0);
			}
			//添加隐患柱状图数据
			if (hiddenDataMap.containsKey(string)){
				hiddenCount.add(hiddenDataMap.get(string));
			}else{
				hiddenCount.add(0);
			}
		}
		jsonObject.put("warningData", warningCount);
		jsonObject.put("hiddenData", hiddenCount);
		return jsonObject;
	}
}
