package i9.defence.platform.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.cache.ErrorTypeCache;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.utils.StringUtil;

/** 
* 创建时间：2018年4月10日 下午4:43:14
* @author  lby
* @version  
* 
*/
public class ChannelDataComponent {
	
	/**
	 * 通道数据
	 */
	private List<ChannelData> channelDatas;
	
	/**
	 * 通道信息
	 */
	private List<Passageway> passageWays;
	
	public ChannelDataComponent setChannelDataComponent(List<ChannelData> channelDatas) {
		this.channelDatas=channelDatas;
		return this;
	}
	
	public ChannelDataComponent setPassageways(List<Passageway> passageWays) {
		this.passageWays=passageWays;
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
		
		//将通道名称传到map中
		Map<Integer, String> nameMap = new HashMap<Integer, String>();
		for (Passageway passageway : passageWays) {
			nameMap.put(passageway.getChannel(), passageway.getName());
		}
		
		
		//将数据按通道分类后传去前台
		JSONArray jsonArray = new JSONArray();
		//日期数组
		List<String> dateList = new ArrayList<>();
		boolean flag = true;
		for (Map.Entry<Integer, List<ChannelData>> entry : map.entrySet()) {
			JSONObject channelObject = new JSONObject();
			channelObject.put("channelNumber", entry.getKey());
			channelObject.put("name", nameMap.get(entry.getKey()));
			//值的数组
			List<String> list = new ArrayList<>();
			for (ChannelData channelData : entry.getValue()) {
				list.add(channelData.getValue());
			}
			//只存一次日期
			if (flag) {
				for (ChannelData channelData : entry.getValue()) {
					dateList.add(StringUtil.dateToStringByRep(channelData.getDateTime(), "yyyy/MM/dd HH:mm:ss"));
				}
				flag = false;
			}
			channelObject.put("value", list);
			jsonArray.add(channelObject);
		}
		jsonObject.put("date", dateList);
		jsonObject.put("channelData", jsonArray);
		return jsonObject;
	}
	
	/**
	 * 故障记录按通道分类
	 * @return
	 */
	public JSONObject errorBuild(){
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
		
		//将通道名称传到map中
		Map<Integer, String> nameMap = new HashMap<Integer, String>();
		for (Passageway passageway : passageWays) {
			nameMap.put(passageway.getChannel(), passageway.getName());
		}
		
		//将数据按通道分类后传去前台
		JSONArray jsonArray = new JSONArray();
		for (Map.Entry<Integer, List<ChannelData>> entry : map.entrySet()) {
			JSONObject channelObject = new JSONObject();
			channelObject.put("channelNumber", entry.getKey());
			channelObject.put("name", nameMap.get(entry.getKey()));
			//值的数组
			JSONArray jsonArrays = new JSONArray();
			for (ChannelData channelData : entry.getValue()) {
				channelData.setErrorName(ErrorTypeCache.getCacheDict(channelData.getValue()));
				jsonArrays.add(JSONObject.toJSON(channelData));
			}
			channelObject.put("value", jsonArrays);
			jsonArray.add(channelObject);
		}
		jsonObject.put("channelData", jsonArray);
		return jsonObject;
	}
}
