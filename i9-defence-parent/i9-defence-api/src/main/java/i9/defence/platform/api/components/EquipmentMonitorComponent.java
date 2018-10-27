package i9.defence.platform.api.components;

import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.Equipment;

/**
 * 创建时间：2018年4月11日 上午10:00:02
 * 
 * @author lby
 * @version
 * 
 */
public class EquipmentMonitorComponent {

    private Equipment equipment;

    public EquipmentMonitorComponent setEquipment(Equipment equipment) {
        this.equipment = equipment;
        return this;
    }

    public JSONObject build() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", equipment.getEquipmentName());
        jsonObject.put("location", equipment.getEquipmentPosition());
        jsonObject.put("remark", equipment.getEquipmentRemarks());
        jsonObject.put("deviceId", equipment.getDeviceId());
        jsonObject.put("eqCategoryName", equipment.getEquipmentCategoryName());
        return jsonObject;
    }
}
