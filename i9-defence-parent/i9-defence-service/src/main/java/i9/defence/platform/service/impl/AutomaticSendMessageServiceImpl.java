package i9.defence.platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.AutomaticSendMessageService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.AliyunSMSEnum;
import i9.defence.platform.utils.AliyunUtil;

/**
 * 自动发送短信Impl
 * @ClassName: automaticSendMessageServiceImpl 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月9日 上午11:14:13
 */
@Service
public class AutomaticSendMessageServiceImpl implements AutomaticSendMessageService {

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private ProjectService projectService;
	
	@Override
	public  void AutomaticSendMessage(String deviceId, Integer equipmentType) {
		//发送离线短信
        //1根据deviceId查找设备
        Equipment equipment = equipmentService.getEquipmentByIdentifier(deviceId);
        //2根据ProjectId查找Project
        Project project = projectService.getProjectById(equipment.getProjectId());
        //2.1判断发送状态 0：不发送 1：发送
		if(1==project.getSendStatus()) {
			JSONArray clientNamesjsonArray = new JSONArray();
			JSONObject clientPhonesjsonObject = new JSONObject();
			JSONObject clientSignNamesjsonObject = new JSONObject();
			List<String> PhonsList = new ArrayList<String>();
			List<String> SignNameList = new ArrayList<String>();
	        String[] recipients = project.getRecipients().split(",");
	        String[] recipientphones = project.getRecipientphones().split(",");
	        for(int i=0;i<recipients.length;i++) {
	        	JSONObject clientNamesJson = new JSONObject();
	        	clientNamesJson.put("name", recipients[i]);
	        	clientNamesJson.put("project", project.getProjectName());
	        	clientNamesJson.put("postion", equipment.getEquipmentRemarks());
	        	clientNamesJson.put("equipmentType",equipment.getEquipmentCategory().getEqCategoryName());
	        	clientNamesjsonArray.add(clientNamesJson);
	        	PhonsList.add(recipientphones[i]); 
	        	SignNameList.add("合极电气"); 
			}
	        clientPhonesjsonObject.put("phones", PhonsList);
	        clientSignNamesjsonObject.put("signNames", SignNameList);
	        Map<String,Object> phonesMap=(Map<String, Object>)clientPhonesjsonObject.parse(clientPhonesjsonObject.toJSONString());
	        Map<String,Object> signNamesMap=(Map<String, Object>)clientSignNamesjsonObject.parse(clientSignNamesjsonObject.toJSONString());
			//2.2获取设备发送类型(0:报警，1:离线，2：隐患)
			String[] splitType = project.getSendType().split(",");
			//2.3将String数组转换成int数组
			int[] arr =new int[splitType.length];
			for(int i=0;i<splitType.length;i++) {
				arr[i] =Integer.parseInt(splitType[i]);
			}
			//2.4遍历int数组
			for(int h=0;h<arr.length;h++) {
				//2.5若SendType=0并且alertStatus=1则发送报警短信
				if(0==arr[h] && 1 == equipmentType) {
					AliyunUtil.sendInfo(AliyunSMSEnum.WANING,phonesMap.get("phones").toString(), clientNamesjsonArray.toJSONString(),signNamesMap.get("signNames").toString() );
				//2.6若SendType=2并且alertStatus=2则发送隐患短信
				}else if(2==arr[h] && 2 == equipmentType) {
					AliyunUtil.sendInfo(AliyunSMSEnum.HIDDENDANGER,phonesMap.get("phones").toString(), clientNamesjsonArray.toJSONString(),signNamesMap.get("signNames").toString() );
				//2.7若SendType=0并且status=1则发送离线短信
				}
				if(1==arr[h] && 0 == equipmentType) {
					AliyunUtil.sendInfo(AliyunSMSEnum.OUTOFLINE,phonesMap.get("phones").toString(), clientNamesjsonArray.toJSONString(),signNamesMap.get("signNames").toString() );
				}
				
			}
		}
	}

}
