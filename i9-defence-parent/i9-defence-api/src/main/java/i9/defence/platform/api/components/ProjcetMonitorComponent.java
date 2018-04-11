package i9.defence.platform.api.components;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.Project;

/** 
* 创建时间：2018年4月11日 上午10:00:02
* @author  lby
* @version  
* 
*/
public class ProjcetMonitorComponent {
	private Project project;
	
	public ProjcetMonitorComponent setProject(Project project) {
		this.project=project;
		return this;
	}
	
	
	public JSONObject build(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", project.getProjectName());
		jsonObject.put("manager", project.getClientListStr());
		jsonObject.put("safeManager", project.getSafeListStr());
		return jsonObject;
	}
}
