package i9.defence.platform.api.components;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.Passageway;

/** 
* 创建时间：2018年4月12日 下午2:19:12
* @author  lby
* @version  
* 
*/
public class PassagewayInfoComponent {
	
	private Passageway passageway;
	
	public PassagewayInfoComponent setPassageway(Passageway passageway){
		this.passageway = passageway;
		return this;
	}
	
	public JSONObject build(){
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("channel", passageway.getChannel());
		jSONObject.put("name", passageway.getName());
		jSONObject.put("hiddenDangerId", passageway.getHiddenDangerId());
		return jSONObject;
	}
}
