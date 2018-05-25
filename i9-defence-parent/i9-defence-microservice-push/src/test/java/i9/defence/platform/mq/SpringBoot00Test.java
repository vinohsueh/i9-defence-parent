package i9.defence.platform.mq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.microservice.push.Application;
import i9.defence.platform.microservice.push.service.ThirdPlatformService;
import i9.defence.platform.microservice.push.vo.ChannelData;
import i9.defence.platform.microservice.push.vo.DeviceInfoDto;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.SqlUtil;
import i9.defence.platform.utils.StringUtil;

/** 
* 创建时间：2018年3月26日 上午9:14:18
* @author  lby
* @version  
* 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SpringBoot00Test {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBoot00Test.class);
	
	@Test
	public void test() {
		thirdPlatformService.createTableEveryday();
	}
	
	@Test
	public void test2() {
		try {
			String textMessage = "{\"systemId\":\"000000000001\",\"deviceAddress\":\"1000FFD7\",\"unit\":16,\"loop\":0,\"dataList\":[{\"datetime\":\"2018-05-06#17:02:36\",\"len\":2,\"data\":\"01B3\",\"channel\":0,\"source\":0,\"type\":2,\"value\":435},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":2,\"data\":\"01A7\",\"channel\":1,\"source\":0,\"type\":2,\"value\":423},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":2,\"data\":\"01D6\",\"channel\":2,\"source\":0,\"type\":2,\"value\":470},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":2,\"data\":\"0170\",\"channel\":3,\"source\":0,\"type\":2,\"value\":368},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"0000011B\",\"channel\":4,\"source\":0,\"type\":5,\"value\":3.97E-43},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000024\",\"channel\":5,\"source\":0,\"type\":5,\"value\":5.0E-44},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000057\",\"channel\":6,\"source\":0,\"type\":5,\"value\":1.22E-43},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000057\",\"channel\":7,\"source\":0,\"type\":5,\"value\":1.22E-43},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000005\",\"channel\":8,\"source\":0,\"type\":0,\"value\":\"05000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000005\",\"channel\":9,\"source\":0,\"type\":0,\"value\":\"05000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000005\",\"channel\":10,\"source\":0,\"type\":0,\"value\":\"05000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000005\",\"channel\":11,\"source\":0,\"type\":0,\"value\":\"05000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000000\",\"channel\":12,\"source\":0,\"type\":0,\"value\":\"00000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000006\",\"channel\":13,\"source\":0,\"type\":0,\"value\":\"06000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000006\",\"channel\":14,\"source\":0,\"type\":0,\"value\":\"06000000\"},{\"datetime\":\"2018-05-06#17:02:36\",\"len\":4,\"data\":\"00000006\",\"channel\":15,\"source\":0,\"type\":0,\"value\":\"06000000\"}],\"systemType\":\"0002\",\"source\":3,\"dataLen\":200}";
			JSONObject jsonObject = JSONObject.parseObject(textMessage);
			String systemId = jsonObject.getString("systemId");
            int loop = jsonObject.getInteger("loop");
            String address = jsonObject.getString("deviceAddress");
            // 通过设备唯一标识更新状态
            String deviceId = StringUtil.getDeviceId(systemId, loop, address);
            //查询故障代码对应的中文名称
            Map<String, String> map = thirdPlatformService.selectDeviceErrors();
            //查询设备信息
            DeviceInfoDto deviceInfoDto = thirdPlatformService.selectEquipmentInfo(deviceId);
            if (deviceInfoDto != null) {
            	//获取通道数据
        		List<ChannelData> list = new ArrayList<ChannelData>();
        		JSONArray object = (JSONArray) jsonObject.get("dataList");
        		for (Object object2 : object) {
        			JSONObject jsonObject2 = (JSONObject)object2;
        			//数据的类型
        			int type = (int) jsonObject2.get("type");
        			String code = String.valueOf(jsonObject2.get("value"));
        			//如果数据类型是0 且 错误代码不为00000000  时  记录记录
        			if (0 == type && !SqlUtil.NORMAL_CODE.equals(code)) {
        				ChannelData channelData = new ChannelData();
        				//回路号
        				channelData.setLoop(loop);
        				//通道号
        				channelData.setChannel((int) jsonObject2.get("channel"));
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
		} catch (BusinessException e) {
			logger.error("err:{},exe:{}",e.getErrorMessage(),e.getExceptionMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Autowired
	private ThirdPlatformService thirdPlatformService;
}
