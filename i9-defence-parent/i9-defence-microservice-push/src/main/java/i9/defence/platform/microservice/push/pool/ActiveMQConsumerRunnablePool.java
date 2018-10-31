package i9.defence.platform.microservice.push.pool;

import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
import i9.defence.platform.utils.DateUtils;
import i9.defence.platform.utils.SqlUtil;
import i9.defence.platform.utils.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class ActiveMQConsumerRunnablePool {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumerRunnablePool.class);

    private final ExecutorService pool = Executors.newFixedThreadPool(50);

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
                    // 查询故障代码对应的中文名称
                    Map<String, String> map = thirdPlatformService.selectDeviceErrors();
                    // 查询设备信息
                    DeviceInfoDto deviceInfoDto = thirdPlatformService.selectEquipmentInfo(deviceId);
                    // 查询设备所需要的那几个通道 不在此这些通道的报警数据不要
                    List<Integer> channels = thirdPlatformService.selectUsefulChannel(deviceId);
                    if (deviceInfoDto == null) {
                        return;
                    }
                    // 获取通道数据
                    List<ChannelData> channelDatas = new ArrayList<ChannelData>();
                    JSONArray dataList = (JSONArray) jsonObject.getJSONArray("dataList");
                    for (int index = 0; index < dataList.size(); index++) {
                        JSONObject dataItem = dataList.getJSONObject(index);
                        int type = dataItem.getIntValue("type");
                        String code = dataItem.getString("value");
                        int channel = dataItem.getIntValue("channel");
                        // 如果数据类型是0 且 错误代码不为00000000 时 记录记录
                        logger.info("设备通道 : {}, 类型 : {}, 值 : {}", channel, type, code);
                        if (channels.contains(channel) && 0 == type && !SqlUtil.NORMAL_CODE.equals(code)) {
                            String codeName = map.get(code + deviceInfoDto.getEquipmentId());
                            if (StringUtils.isBlank(codeName)) {
                                codeName = "未知错误";
                            }
                            Date createTime = DateUtils.parseDate(dataItem.getString("datetime").replace("#", " "));
                            ChannelData channelData = new ChannelData(loop, channel, codeName, createTime);
                            channelDatas.add(channelData);
                        }
                    }
                    thirdPlatformService.saveAlertOrigin(deviceInfoDto, channelDatas);
                    thirdPlatformService.updateDeviceStatus(deviceInfoDto.getId(), channelDatas.size());
                    logger.info("push message : " + textMessage.toString());
                } catch (Exception e) {
                    logger.error("save up stream decode error, ex : ", e);
                }
            }
        });
    }

}
