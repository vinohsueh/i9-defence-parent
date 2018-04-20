package i9.defence.platform.api.quartz;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/** 
* 创建时间：2018年4月19日 上午9:50:13
* @author  lby
* @version  
* 
*/
@Configuration          //证明这个类是一个配置文件  
@EnableScheduling       //打开quartz定时器总开关  
public class Timer {
	
	/*@Scheduled(cron="* * 0/7  * * ? ") //1秒执行一次
	public void stepTimer(){  
	    System.out.println(1);
	    
	}  */
}
