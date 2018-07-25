package i9.defence.platform.api.components;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.Client;

/**
 * @ClassName: ManagerSearchComponent 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年7月25日 下午8:33:14
 */
public class ClientSearchComponent {
	
	/**
	 * 客户信息
	 */
	private Client  client;

	public ClientSearchComponent setClient(Client client) {
		this.client = client;
		return this;
	}
	
	public JSONObject build() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",client.getId());
		jsonObject.put("name",client.getName());
		return jsonObject; 
	}
	
}
