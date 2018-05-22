package i9.defence.platform.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import i9.defence.platform.microservice.push.Application;
import i9.defence.platform.microservice.push.service.ThirdPlatformService;

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
		thirdPlatformService.createTableEveryday();
	}
	
	@Autowired
	private ThirdPlatformService thirdPlatformService;
}
