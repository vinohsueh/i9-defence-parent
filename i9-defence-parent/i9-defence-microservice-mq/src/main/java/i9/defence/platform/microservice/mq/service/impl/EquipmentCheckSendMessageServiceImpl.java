package i9.defence.platform.microservice.mq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.microservice.mq.service.EquipmentCheckSendMessageService;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Project;
import i9.defence.platform.mq.libraries.destination.ActiveMQQueueEnum;
import i9.defence.platform.mq.libraries.producer.ActiveMQProducerService;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.service.ThirdPlatformService;
import i9.defence.platform.service.dto.ChannelData;
import i9.defence.platform.service.dto.DeviceInfoDto;
import i9.defence.platform.utils.AliyunSMSEnum;
import i9.defence.platform.utils.DateUtils;
import i9.defence.platform.utils.SqlUtil;

/**
 * 自动发送短信Impl
 * 
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
    @Autowired
    private ThirdPlatformService thirdPlatformService;

    public HashSet<Integer> stringSplit(String str) {
        if (StringUtils.isBlank(str)) {
            return new HashSet<Integer>();
        }
        String[] arr = str.split(",");
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (String s : arr) {
            hashSet.add(Integer.parseInt(s));
        }
        return hashSet;
    }

    /**
     * 发送设备状态短信
     */
    @Override
    public void checkEquipmentAndSendMessageAlarm(String deviceId, int alertStatus, String jsonStr) {
        // 1根据deviceId查找设备
        Equipment equipment = equipmentService.getEquipmentByIdentifier(deviceId);
        // 2根据ProjectId查找Project
        Project project = projectService.getProjectById(equipment.getProjectId());
        // 2.1判断发送状态 0：不发送 1：发送
        if (project.getSendStatus() != 1) {
            return;
        }

        JSONArray clientNames = new JSONArray();
        List<String> phonsList = new ArrayList<String>();
        List<String> signNameList = new ArrayList<String>();
        String[] recipientphones = project.getRecipientphones().split(",");
        for (int i = 0; i < recipientphones.length; i++) {
            JSONObject clientNamesJson = new JSONObject();
            clientNamesJson.put("time", equipment.getEquipmentNewEventTimeStr());
            clientNamesJson.put("project", project.getProjectName());
            clientNamesJson.put("postion", equipment.getEquipmentRemarks());
//          clientNamesJson.put("equipmentType", equipment.getEquipmentCategory().getEqCategoryName());
            clientNames.add(clientNamesJson);
            phonsList.add(recipientphones[i]);
            signNameList.add("合极电气");
        }
        final String phones = JSONObject.toJSONString(phonsList);
        final String signNames = JSONObject.toJSONString(signNameList);

        SMSPushBean b = new SMSPushBean();
        b.phones = phones;
        b.signNames = signNames;
        // b.clientNames = clientNames.toJSONString();

        // 查询设备所需要的那几个通道 不在此这些通道的报警数据不要
        List<Integer> channels = thirdPlatformService.selectUsefulChannel(deviceId);
        // 查询故障代码对应的中文名称
        Map<String, String> map = thirdPlatformService.selectDeviceErrors();
        // 查询设备信息
        DeviceInfoDto deviceInfoDto = thirdPlatformService.selectEquipmentInfo(deviceId);

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        int loop = jsonObject.getInteger("loop");
        List<ChannelData> channelDatas = new ArrayList<ChannelData>();
        JSONArray dataList = jsonObject.getJSONArray("dataList");
        for (int index = 0; index < dataList.size(); index++) {
            JSONObject dataItem = dataList.getJSONObject(index);
            int type = (int) dataItem.getIntValue("type");
            String code = dataItem.getString("value");
            int channel = dataItem.getIntValue("channel");
            // 如果数据类型是0 且 错误代码不为00000000 时 记录记录
            if (channels.contains(channel) && 0 == type && !SqlUtil.NORMAL_CODE.equals(code)) {
                String codeName = map.get(code + deviceInfoDto.getEquipmentId());// 错误代码转为中文错误名称
                if (StringUtils.isBlank(codeName)) {
                    codeName = "未知错误";
                }
                Date createTime = DateUtils.parseDate(dataItem.getString("datetime").replace("#", " "));
                ChannelData channelData = new ChannelData(loop, channel, codeName, createTime);
                channelDatas.add(channelData);
            }
        }
        // 中文错误已经放入channelDatas
        String warnType = "";
        StringBuffer stringBuffer = new StringBuffer();
        for (ChannelData channelData : channelDatas) {
            stringBuffer.append(",").append(channelData.getCode());
        }
        if (!channelDatas.isEmpty()) {
            warnType = stringBuffer.substring(1);
        }
        for (int i = 0; i < clientNames.size(); i++) {
            clientNames.getJSONObject(i).put("warnType", warnType);
        }
        b.clientNames = clientNames.toJSONString();

        // 2.2获取设备发送类型(0:报警，1:离线，2：隐患)
        HashSet<Integer> ss = this.stringSplit(project.getSendType());
        // 2.4遍历int数组
        for (int h : ss) {
            if (0 == h && 1 == alertStatus) {
                // 2.5若SendType=0并且alertStatus=1则发送报警短信
                b.aliyunSMSEnum = AliyunSMSEnum.WANING;
                this.sendSMS(b);
            } else if (2 == h && 2 == alertStatus) {
                // 2.6若SendType=2并且alertStatus=2则发送隐患短信
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
        JSONObject jsonObject = new JSONObject();
        // 发送短信模版
        jsonObject.put("templateNum", b.aliyunSMSEnum.getTemplateNum());
        jsonObject.put("phones", b.phones);
        jsonObject.put("clientNames", b.clientNames);
        jsonObject.put("signNames", b.signNames);
        activeMQProducerService.sendMessage(ActiveMQQueueEnum.I9_SMS, jsonObject.toJSONString());
    }

    @Autowired
    private ActiveMQProducerService activeMQProducerService;

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

        JSONArray clientNames = new JSONArray();
        List<String> phonsList = new ArrayList<String>();
        List<String> signNameList = new ArrayList<String>();
        String[] recipientphones = project.getRecipientphones().split(",");
        for (int i = 0; i < recipientphones.length; i++) {
            JSONObject clientNamesJson = new JSONObject();
            clientNamesJson.put("time", equipment.getEquipmentNewEventTimeStr());
            clientNamesJson.put("project", project.getProjectName());
            clientNamesJson.put("postion", equipment.getEquipmentRemarks());
//            clientNamesJson.put("equipmentType", equipment.getEquipmentCategory().getEqCategoryName());
            clientNames.add(clientNamesJson);
            phonsList.add(recipientphones[i]);
            signNameList.add("合极电气");
        }

        final String phones = JSONObject.toJSONString(phonsList);
        final String signNames = JSONObject.toJSONString(signNameList);

        // 2.2获取设备发送类型(0:报警，1:离线，2：隐患)
        HashSet<Integer> ss = this.stringSplit(project.getSendType());
        if (ss.contains(1)) {
            SMSPushBean b = new SMSPushBean();
            b.phones = phones;
            b.signNames = signNames;
            b.clientNames = clientNames.toJSONString();
            b.aliyunSMSEnum = AliyunSMSEnum.OUTOFLINE;
            this.sendSMS(b);
        }
    }
}
