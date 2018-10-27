package i9.defence.platform.api.components;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.Project;

/**
 * 创建时间：2018年4月11日 上午10:00:02
 * 
 * @author lby
 * @version
 * 
 */
public class ProjcetMonitorComponent {

    private Project project;

    public ProjcetMonitorComponent setProject(Project project) {
        this.project = project;
        return this;
    }

    public JSONObject build() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", project.getId());
        jsonObject.put("name", project.getProjectName());
        jsonObject.put("manager", project.getClientListStr());
        jsonObject.put("safeManager", project.getSafeListStr());
        jsonObject.put("location", project.getProjectAddressStr());
        return jsonObject;
    }

    public JSONObject build2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", project.getId());
        jsonObject.put("projectName", project.getProjectName());
        jsonObject.put("projectLongitude", project.getProjectLongitude());
        jsonObject.put("projectLatitude", project.getProjectLatitude());
        jsonObject.put("address", project.getProjectAddress());
        jsonObject.put("area", project.getProjectAddress());
        jsonObject.put("clientListStr", project.getClientListStr());
        jsonObject.put("saferList", project.getSafeListStr());
        jsonObject.put("equipmentStatis", project.getEquipmentStatis());
        if (project.getDistributor() != null) {
            jsonObject.put("distributorName", project.getDistributor().getName());
        }
        jsonObject.put("warningCount", project.getWarningCount());
        return jsonObject;
    }
}
