package i9.defence.platform.microservice.push.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import i9.defence.platform.microservice.push.service.ThirdPlatformService;

/** 
* 创建时间：2018年4月19日 上午9:50:13
* @author  lby
* @version  
* 
*/
@Configuration          //证明这个类是一个配置文件  
@EnableScheduling       //打开quartz定时器总开关  
public class Timer {
	
	@Autowired
	private ThirdPlatformService thirdPlatformService;
	
	//每天0时生成一张当天的表
	@Scheduled(cron="0 0 0 * * ?") 
	public void createTableTimer(){  
		thirdPlatformService.createTableEveryday();
	}  
}
