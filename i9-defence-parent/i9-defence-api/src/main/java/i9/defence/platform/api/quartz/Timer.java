package i9.defence.platform.api.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import i9.defence.platform.service.ErrHandleService;
import i9.defence.platform.service.HiddenDangerService;

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
	private HiddenDangerService hiddenDangerService;
	@Autowired
	private ErrHandleService errHandleService;
	//每7天查询一下 设备 如果有隐患或者报警的  生成待处理记录
	@Scheduled(cron="0 0 0 1/7 * ? ") //7天执行一次
	public void stepTimer(){  
		//查询有问题的设备 标识
		List<String> list = hiddenDangerService.selectErrorEquipmentIds();
		//批量处理问题设备
		errHandleService.handleErrorDevice(list);
	}  
}
