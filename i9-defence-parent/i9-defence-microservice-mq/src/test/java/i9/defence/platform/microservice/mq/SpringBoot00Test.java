package i9.defence.platform.microservice.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import i9.defence.platform.microservice.mq.Application;
import i9.defence.platform.service.UpStreamDecodeService;

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

	@Test
	public void test() {
	/*	 //通过设备唯一标识更新状态
        String deviceId = \\"000000000001000000001000FFD7\\";
        int status = 0;  //0 掉线   1  离线 
        Date date = new Date();
        upStreamDecodeService.updateEquipmentStatus(deviceId,status);
        
        //插入离线掉线 记录 
        ConnectLog connectLog = new ConnectLog();
        connectLog.setDeviceId(deviceId);//设备唯一标识
        connectLog.setStatus(status);
        connectLog.setCreateTime(date);//时间
        upStreamDecodeService.insertConnectRecord(connectLog);*/
		String textMessage = "{\"systemId\":\"000000000001\",\"deviceAddress\":\"1000F028\",\"unit\":16,\"loop\":0,\"dataList\":[{\"datetime\":\"2018-10-29#15:58:07\",\"len\":2,\"data\":\"0006\",\"channel\":0,\"source\":0,\"type\":2,\"value\":6},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":2,\"data\":\"01DE\",\"channel\":1,\"source\":0,\"type\":2,\"value\":478},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":2,\"data\":\"0209\",\"channel\":2,\"source\":0,\"type\":2,\"value\":521},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":2,\"data\":\"01BB\",\"channel\":3,\"source\":0,\"type\":2,\"value\":443},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"0000027F\",\"channel\":4,\"source\":0,\"type\":5,\"value\":63.9},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000024\",\"channel\":5,\"source\":0,\"type\":5,\"value\":3.6},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000057\",\"channel\":6,\"source\":0,\"type\":5,\"value\":8.7},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000024\",\"channel\":7,\"source\":0,\"type\":5,\"value\":3.6},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000000\",\"channel\":8,\"source\":0,\"type\":0,\"value\":\"00000000\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000005\",\"channel\":9,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000005\",\"channel\":10,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000005\",\"channel\":11,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000000\",\"channel\":12,\"source\":0,\"type\":0,\"value\":\"00000000\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000005\",\"channel\":13,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000005\",\"channel\":14,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-10-29#15:58:07\",\"len\":4,\"data\":\"00000005\",\"channel\":15,\"source\":0,\"type\":0,\"value\":\"00000005\"}],\"systemType\":\"0002\",\"source\":3,\"dataLen\":200}";
		try {
			upStreamDecodeService.saveUpStreamDecode(textMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	private UpStreamDecodeService upStreamDecodeService;
}
