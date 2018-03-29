package i9.defence.platform.api.component;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.ChannelData;
import i9.defence.platform.utils.StringUtil;

/** 
* 创建时间：2018年3月29日 上午9:20:44
* @author  lby
* @version  
* 
*/
public class ChannelDataInfoComponent {
	
	private ChannelData channelData;
	
	public ChannelDataInfoComponent setChannelData(ChannelData channelData){
		this.channelData = channelData;
		return this;
	}
	
	public JSONObject build(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("channel", channelData.getChannel());
		jsonObject.put("time", StringUtil.dateToStringByRep(channelData.getDateTime(), "yyyy-MM-dd HH:mm:ss"));
		jsonObject.put("value", channelData.getValue());
		return jsonObject;
	}
}
