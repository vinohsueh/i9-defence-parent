package i9.defence.platform.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.ChannelData;
import i9.defence.platform.utils.StringUtil;

/** 
* 创建时间：2018年4月10日 下午4:43:14
* @author  lby
* @version  
* 
*/
public class ChannelDataComponent {
	
	private List<ChannelData> channelDatas;
	
	public ChannelDataComponent setChannelDataComponent(List<ChannelData> channelDatas) {
		this.channelDatas=channelDatas;
		return this;
	}
	
	public JSONObject build(){
		JSONObject jsonObject = new JSONObject();
		//将通道数据按通道号放入map中
		Map<Integer, List<ChannelData>> map = new HashMap<Integer, List<ChannelData>>();
		for (ChannelData channelData : channelDatas) {
			List<ChannelData> list = null;
			if (map.containsKey(channelData.getChannel())) {
				list = map.get(channelData.getChannel());
			}else{
				list = new ArrayList<ChannelData>();
			}
			list.add(channelData);
			map.put(channelData.getChannel(), list);
		}
		
		//将数据按通道分类后传去前台
		JSONArray jsonArray = new JSONArray();
		for (Map.Entry<Integer, List<ChannelData>> entry : map.entrySet()) {
			JSONObject channelObject = new JSONObject();
			channelObject.put("channelNumber", entry.getKey());
			//值的数组
			List<String> list = new ArrayList<>();
			for (ChannelData channelData : entry.getValue()) {
				list.add(channelData.getValue());
			}
			channelObject.put("value", list);
			jsonArray.add(channelObject);
		}
		
		//日期数组
		List<String> dateList = new ArrayList<>();
		for (ChannelData channelData : map.get(0)) {
			dateList.add(StringUtil.dateToStringByRep(channelData.getDateTime(), "yyyy/MM/dd HH:mm:ss"));
		}
		
		jsonObject.put("date", dateList);
		jsonObject.put("channelData", jsonArray);
		return jsonObject;
	}
}
