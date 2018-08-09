package i9.defence.platform.microservice.mq.service.impl;

import i9.defence.platform.microservice.mq.service.ActiveMQConsumerTask;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Project;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ProjectService;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.AliyunSMSEnum;
import i9.defence.platform.utils.AliyunUtil;
import i9.defence.platform.utils.DateUtils;
import i9.defence.platform.utils.StringUtil;

import java.util.Date;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * 处理ActiveMQ设备连接消息通知任务
 * 
 * @author r12
 * 
 */
public class ActiveMQDeviceStatusConsumerTask extends ActiveMQConsumerTask {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private ProjectService projectService;

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQDeviceStatusConsumerTask.class);

    private final TextMessage textMessage;

    public ActiveMQDeviceStatusConsumerTask(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void run() {
        UpStreamDecodeService upStreamDecodeService = getUpStreamDecodeService();
        try {
            JSONObject jsonObject = JSONObject.parseObject(textMessage.getText());

            String systemId = jsonObject.getString("systemId");
            int loop = jsonObject.getInteger("loop");
            String address = jsonObject.getString("deviceAddress");
            int status = jsonObject.getIntValue("status");
            String channelId = jsonObject.getString("channelId");
            
            // 通过设备唯一标识更新状态
            String deviceId = StringUtil.getDeviceId(systemId, loop, address);
//            int status = 1;
            upStreamDecodeService.updateEquipmentStatus(deviceId, status);
            logger.info("update device status success, deviceId : {}, status : {}", deviceId, status);

            // 插入离线掉线 记录
            ConnectLog connectLog = new ConnectLog();
            connectLog.setDeviceId(deviceId);// 设备唯一标识
            connectLog.setStatus(status);
            connectLog.setChannelId(channelId);
            
            String submitDate = jsonObject.getString("submitDate");
            if (submitDate == null || submitDate.equals("")) {
                // 兼容之前MQ消息
                connectLog.setCreateTime(new Date());// 时间
            }
            else {
                Date date = DateUtils.parseDate(submitDate);
                connectLog.setCreateTime(date);
            }
            
            upStreamDecodeService.insertConnectRecord(connectLog);
            
            //发送离线短信
            //1根据deviceId查找设备
            Equipment equipment = equipmentService.getEquipmentByIdentifier(deviceId);
            //2根据ProjectId查找Project
            Project project = projectService.getProjectById(equipment.getProjectId());
            //2.1判断发送状态 0：不发送 1：发送
			if(1==project.getSendStatus()) {
				StringBuffer clientNamesBuffer = new StringBuffer("[");
		        StringBuffer clientPhonesBuffer = new StringBuffer("[\"");
		        StringBuffer clientSignNamesBuffer = new StringBuffer("[\"");
		        String[] recipients = project.getRecipients().split(",");
		        String[] recipientphones = project.getRecipientphones().split(",");
		        for(int i=0;i<recipients.length;i++) {
		        	if(i==recipients.length-1) {
		        		clientNamesBuffer.append("{\"name\":\"").append(recipients[i]).append("\","+"\"project\":\""+project.getProjectName()+"\","+"\"postion\":\""+equipment.getEquipmentRemarks()+"\","+"\"equipmentType\":\""+equipment.getEquipmentCategory().getEqCategoryName()+"\"").append("}]");
		        		clientPhonesBuffer.append(recipientphones[i]).append("\"]");
		        		clientSignNamesBuffer.append("合极电气").append("\"]");
		        	}else {
		        		clientNamesBuffer.append("{\"name\":\"").append(recipients[i]).append("\","+"\"project\":\""+project.getProjectName()+"\","+"\"postion\":\""+equipment.getEquipmentRemarks()+"\","+"\"equipmentType\":\""+equipment.getEquipmentCategory().getEqCategoryName()+"\"},");
		        		clientPhonesBuffer.append(recipientphones[i]).append("\",\"");
		        		clientSignNamesBuffer.append("合极电气").append("\",\"");
		        	}
				}
				//2.2获取设备发送类型(0:报警，1:离线，2：隐患)
				String[] splitType = project.getSendType().split(",");
				//2.3将String数组转换成int数组
				int[] arr =new int[splitType.length];
				for(int i=0;i<splitType.length;i++) {
					arr[i] =Integer.parseInt(splitType[i]);
				}
				//2.4遍历int数组
				for(int h=0;h<arr.length;h++) {
					//2.5若SendType=0并且status=1则发送离线短信
					if(1==arr[h] && 0 == status) {
						AliyunUtil.sendInfo(AliyunSMSEnum.OUTOFLINE, clientPhonesBuffer.toString(), clientNamesBuffer.toString(), clientSignNamesBuffer.toString());
					}
				}
			}
            logger.info("save connect log success, deviceId : {}, status : {}", deviceId, status);
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }
}
