package i9.defence.platform.api.components;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.vo.MonthData;
import i9.defence.platform.model.Equipment;

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
		return jsonObject;
	}
}
