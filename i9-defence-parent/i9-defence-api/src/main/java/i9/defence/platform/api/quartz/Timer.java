package i9.defence.platform.api.quartz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import i9.defence.platform.dao.vo.EquipmentNewestDto;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ErrHandleService;
import i9.defence.platform.service.HiddenDangerService;
import i9.defence.platform.utils.StringUtil;

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
	@Autowired
    private EquipmentService equipmentService;
	
	//每7天查询一下 设备 如果有隐患或者报警的  生成待处理记录
	@Scheduled(cron="0 0 0 1/7 * ? ") //7天执行一次
	public void stepTimer(){  
		//查询有问题的设备 标识
		List<String> list = hiddenDangerService.selectErrorEquipmentIds();
		//批量处理问题设备
		errHandleService.handleErrorDevice(list);
	}  
	
	//每8分钟查询一下 设备 如果记录时间大于当前时间8分钟，则更新为离线
    @Scheduled(cron="0 0 */1 * * ? ")
    public void stepTimer2() throws ParseException {    
        //查询所有设备最新事件时间
        List<EquipmentNewestDto> list = equipmentService.selectAllEquipmentNewest();
        //新建异常设备id
        ArrayList<String> eqDeviceIdList = new  ArrayList<String>(); 
        //获取当前时间
        Date nowDate = new Date();
        for(EquipmentNewestDto equipment :list) {
            Date equipEventTime = StringUtil.StringToDateS(equipment.getNewEventTime());
            //获取相差分钟数
            long min =(nowDate.getTime()-equipEventTime.getTime())/(1000*60);
            if(min>=8) {
                eqDeviceIdList.add(equipment.getEqDeviceId());
            }
        }
        equipmentService.updateSomeStatusByDevicedIds(eqDeviceIdList);
    } 
}
