package i9.defence.platform.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.vo.HiddenDangerChannelDto;

/** 
* @author user: jiace
* @version creatTime：2018年4月10日 上午11:09:32 
* 
*/
public class HiddendangerChannelDataInFoComponents {

	private List<HiddenDangerChannelDto> hiddenDangerChannelDto;
	
	public HiddendangerChannelDataInFoComponents setHiddenDangerChannelDto(List<HiddenDangerChannelDto> hiddenDangerChannelDto) {
		this.hiddenDangerChannelDto=hiddenDangerChannelDto;
		return this;
	}
	public JSONArray build() {
		JSONArray jsonArray = new JSONArray();
		Map<Integer,List<HiddenDangerChannelDto>> map = new HashMap<Integer,List<HiddenDangerChannelDto>>();
		for (HiddenDangerChannelDto hiddenDangerChannelDtoInFo : hiddenDangerChannelDto) {
			if(map.containsKey(hiddenDangerChannelDtoInFo.getChannel())) {
				List<HiddenDangerChannelDto> list=map.get(hiddenDangerChannelDtoInFo.getChannel());
				list.add(hiddenDangerChannelDtoInFo);
				map.put(hiddenDangerChannelDtoInFo.getChannel(),list);
			}else {
				List<HiddenDangerChannelDto> list= new ArrayList<HiddenDangerChannelDto>();
				list.add(hiddenDangerChannelDtoInFo);
				map.put(hiddenDangerChannelDtoInFo.getChannel(), list);
			}
		}
		for(Map.Entry<Integer,List<HiddenDangerChannelDto>> entry : map.entrySet()) {
			JSONObject jsonObjects = new JSONObject();
			jsonObjects.put("channel", entry.getKey());
			jsonObjects.put("name", entry.getValue().get(0).getName());
			List<HiddenDangerChannelDto> lists = entry.getValue();
			JSONArray jsonArrays = new JSONArray();
			for(HiddenDangerChannelDto hiddenDangerChannelDto :lists) {
				jsonArrays.add(JSONObject.toJSON(hiddenDangerChannelDto));
			}
			jsonObjects.put("data", jsonArrays);
			jsonArray.add(jsonObjects);
		}
		return jsonArray;
    }
}
 