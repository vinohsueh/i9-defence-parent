package i9.defence.platform.microservice.push.pool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
import i9.defence.platform.utils.SqlUtil;
import i9.defence.platform.utils.StringUtil;

@Component
public class ActiveMQConsumerRunnablePool {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumerRunnablePool.class);

    private final ExecutorService pool = Executors.newCachedThreadPool();
    
    @Autowired
    private ThirdPlatformService thirdPlatformService;

    public void executePool(final TextMessage textMessage) {
        pool.execute(new TimerTask() {

            @Override
            public void run() {
                try {
                	JSONObject jsonObject = JSONObject.parseObject(textMessage.getText());
                    String systemId = jsonObject.getString("systemId");
                    int loop = jsonObject.getInteger("loop");
                    String address = jsonObject.getString("deviceAddress");
                    // 通过设备唯一标识更新状态
                    String deviceId = StringUtil.getDeviceId(systemId, loop, address);
                    //查询故障代码对应的中文名称
                    Map<String, String> map = thirdPlatformService.selectDeviceErrors();
                    //查询设备信息
                    DeviceInfoDto deviceInfoDto = thirdPlatformService.selectEquipmentInfo(deviceId);
                    //查询设备所需要的那几个通道   不在此这些通道的报警数据不要
                    List<Integer> channels = thirdPlatformService.selectUsefulChannel(deviceId);
                    if (deviceInfoDto != null) {
                    	//获取通道数据
                		List<ChannelData> list = new ArrayList<ChannelData>();
                		JSONArray object = (JSONArray) jsonObject.get("dataList");
                		for (Object object2 : object) {
                			JSONObject jsonObject2 = (JSONObject)object2;
                			//数据的类型
                			int type = (int) jsonObject2.get("type");
                			String code = String.valueOf(jsonObject2.get("value"));
                			int channel = (int) jsonObject2.get("channel");
                			//如果数据类型是0 且 错误代码不为00000000  时  记录记录
                			if (channels.contains(channel) && 0 == type && !SqlUtil.NORMAL_CODE.equals(code)) {
                				ChannelData channelData = new ChannelData();
                				//回路号
                				channelData.setLoop(loop);
                				//通道号
                				channelData.setChannel(channel);
                				//错误代码
                				//此处转为中文错误名称
                				String codeName = map.get(code+deviceInfoDto.getEquipmentId());
                				if (codeName != null) {
                					channelData.setCode(codeName);
                				}else{
                					channelData.setCode("未知错误");
                				}
                				
                				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    			channelData.setCreateTime(simpleDateFormat.parse((String)(jsonObject2.get("datetime").toString().replace("#", " "))));
                    			list.add(channelData);
                			}
                		}
                		 thirdPlatformService.saveAlertOrigin(deviceInfoDto,list);
            			 thirdPlatformService.updateDeviceStatus(deviceInfoDto.getId(),list.size());
                         logger.info("push message : " + textMessage.toString());
                    }
                } catch (Exception e) {
                    logger.error("save up stream decode error, ex : ", e);
                }
            }
        });
    }

}
