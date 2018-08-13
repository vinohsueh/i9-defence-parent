package i9.defence.platform.microservice.mq.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.microservice.mq.service.EquipmentCheckSendMessageService;
import i9.defence.platform.microservice.mq.util.SpringBeanService;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Project;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.utils.AliyunSMSEnum;
import i9.defence.platform.utils.AliyunUtil;

/**
 * 自动发送短信Impl
 * 
 * @ClassName: automaticSendMessageServiceImpl
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月9日 上午11:14:13
 */
@Service
public class EquipmentCheckSendMessageServiceImpl implements EquipmentCheckSendMessageService {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ProjectService projectService;

    /**
     * 发送设备状态短信
     */
    @Override
    public void checkEquipmentAndSendMessageAlarm(String deviceId, int alertStatus) {
        // 1根据deviceId查找设备
        Equipment equipment = equipmentService.getEquipmentByIdentifier(deviceId);
        // 2根据ProjectId查找Project
        Project project = projectService.getProjectById(equipment.getProjectId());
        // 2.1判断发送状态 0：不发送 1：发送
        if (project.getSendStatus() != 1) {
            return;
        }
        JSONArray clientNamesjsonArray = new JSONArray();
        List<String> PhonsList = new ArrayList<String>();
        List<String> SignNameList = new ArrayList<String>();
        String[] recipients = project.getRecipients().split(",");
        String[] recipientphones = project.getRecipientphones().split(",");
        for (int i = 0; i < recipients.length; i++) {
            JSONObject clientNamesJson = new JSONObject();
            clientNamesJson.put("name", recipients[i]);
            clientNamesJson.put("project", project.getProjectName());
            clientNamesJson.put("postion", equipment.getEquipmentRemarks());
            clientNamesJson.put("equipmentType", equipment.getEquipmentCategory().getEqCategoryName());
            clientNamesjsonArray.add(clientNamesJson);
            PhonsList.add(recipientphones[i]);
            SignNameList.add("合极电气");
        }
        // 2.2获取设备发送类型(0:报警，1:离线，2：隐患)
        String[] splitType = project.getSendType().split(",");
        // 2.3将String数组转换成int数组
        int[] arr = new int[splitType.length];
        for (int i = 0; i < splitType.length; i++) {
            arr[i] = Integer.parseInt(splitType[i]);
        }
        final String phones = JSONObject.toJSONString(PhonsList);
        final String signNames = JSONObject.toJSONString(SignNameList);
        SMSPushBean b = new SMSPushBean();
        b.phones = phones;
        b.signNames = signNames;
        b.clientNames = clientNamesjsonArray.toJSONString();

        // 2.4遍历int数组
        for (int h = 0; h < arr.length; h++) {
            // 2.5若SendType=0并且alertStatus=1则发送报警短信
            if (0 == arr[h] && 1 == alertStatus) {
                b.aliyunSMSEnum = AliyunSMSEnum.WANING;
                this.sendSMS(b);
                // 2.6若SendType=2并且alertStatus=2则发送隐患短信
            } else if (2 == arr[h] && 2 == alertStatus) {
                b.aliyunSMSEnum = AliyunSMSEnum.HIDDENDANGER;
                this.sendSMS(b);
            }
        }
    }

    class SMSPushBean {
        public AliyunSMSEnum aliyunSMSEnum;
        public String phones;
        public String clientNames;
        public String signNames;
    }

    private void sendSMS(SMSPushBean b) {
        ActiveMQProducerService activeMQProducerService = SpringBeanService.getBean(ActiveMQProducerService.class);
        JSONObject jsonObject = new JSONObject();
        // 发送短信模版
        jsonObject.put("templateNum", b.aliyunSMSEnum.getTemplateNum());
        jsonObject.put("phones", b.phones);
        jsonObject.put("clientNames", b.clientNames);
        jsonObject.put("signNames", b.signNames);
        activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_SMS, jsonObject.toJSONString());
    }

    /**
     * 发送设备离线短信
     */
    @Override
    public void checkEquipmentAndSendMessageOffline(String deviceId) {
        // 1根据deviceId查找设备
        Equipment equipment = equipmentService.getEquipmentByIdentifier(deviceId);
        // 2根据ProjectId查找Project
        Project project = projectService.getProjectById(equipment.getProjectId());
        // 2.1判断发送状态 0：不发送 1：发送
        if (project.getSendStatus() != 1) {
            return;
        }
        JSONArray clientNamesjsonArray = new JSONArray();
        List<String> PhonsList = new ArrayList<String>();
        List<String> SignNameList = new ArrayList<String>();
        String[] recipients = project.getRecipients().split(",");
        String[] recipientphones = project.getRecipientphones().split(",");
        for (int i = 0; i < recipients.length; i++) {
            JSONObject clientNamesJson = new JSONObject();
            clientNamesJson.put("name", recipients[i]);
            clientNamesJson.put("project", project.getProjectName());
            clientNamesJson.put("postion", equipment.getEquipmentRemarks());
            clientNamesJson.put("equipmentType", equipment.getEquipmentCategory().getEqCategoryName());
            clientNamesjsonArray.add(clientNamesJson);
            PhonsList.add(recipientphones[i]);
            SignNameList.add("合极电气");
        }
        // 2.2获取设备发送类型(0:报警，1:离线，2：隐患)
        String[] splitType = project.getSendType().split(",");
        // 2.3将String数组转换成int数组
        int[] arr = new int[splitType.length];
        for (int i = 0; i < splitType.length; i++) {
            arr[i] = Integer.parseInt(splitType[i]);
        }
        final String phones = JSONObject.toJSONString(PhonsList);
        final String signNames = JSONObject.toJSONString(SignNameList);
        
        SMSPushBean b = new SMSPushBean();
        b.phones = phones;
        b.signNames = signNames;
        b.clientNames = clientNamesjsonArray.toJSONString();
        b.aliyunSMSEnum = AliyunSMSEnum.OUTOFLINE;
        this.sendSMS(b);
    }

}
