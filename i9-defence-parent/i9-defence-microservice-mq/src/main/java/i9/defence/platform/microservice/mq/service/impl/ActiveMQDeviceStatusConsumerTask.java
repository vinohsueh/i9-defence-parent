package i9.defence.platform.microservice.mq.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.microservice.mq.service.ActiveMQConsumerTask;
import i9.defence.platform.model.ConnectLog;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.StringUtil;

/**
 * 处理ActiveMQ设备连接消息通知任务
 * 
 * @author r12
 * 
 */
public class ActiveMQDeviceStatusConsumerTask extends ActiveMQConsumerTask {

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
            
            JSONArray object = (JSONArray) jsonObject.get("dataList");
            //获取数据传过来的时间
            JSONObject jsonObject2 = (JSONObject) object.get(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date createTime = simpleDateFormat.parse((String)(jsonObject2.get("datetime").toString().replace("#", " ")));
            
            // 通过设备唯一标识更新状态
            String deviceId = StringUtil.getDeviceId(systemId, loop, address);
//            int status = 1;
            upStreamDecodeService.updateEquipmentStatus(deviceId, status);
            logger.info("update device status success, deviceId : {}, status : {}", deviceId, status);

            // 插入离线掉线 记录
            ConnectLog connectLog = new ConnectLog();
            connectLog.setDeviceId(deviceId);// 设备唯一标识
            connectLog.setStatus(status);
            connectLog.setCreateTime(createTime);// 时间
            upStreamDecodeService.insertConnectRecord(connectLog);
            logger.info("save connect log success, deviceId : {}, status : {}", deviceId, status);
        } catch (Exception e) {
            logger.error("save up stream decode error, ex : ", e);
        }
    }
}
