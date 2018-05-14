package i9.defence.platform.mq;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import i9.defence.platform.microservice.mq.Application;
import i9.defence.platform.model.ConnectLog;
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
		 //通过设备唯一标识更新状态
        String deviceId = "000000000001000000001000FFD7";
        int status = 0;  //0 掉线   1  离线 
        Date date = new Date();
        upStreamDecodeService.updateEquipmentStatus(deviceId,status);
        
        //插入离线掉线 记录 
        ConnectLog connectLog = new ConnectLog();
        connectLog.setDeviceId(deviceId);//设备唯一标识
        connectLog.setStatus(status);
        connectLog.setCreateTime(date);//时间
        upStreamDecodeService.insertConnectRecord(connectLog);
	}
	
	@Autowired
	private UpStreamDecodeService upStreamDecodeService;
}
