package i9.defence.platform.microservice.mq.sms;

import i9.defence.platform.microservice.mq.Application;
import i9.defence.platform.microservice.mq.service.EquipmentCheckSendMessageService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * EquipmentCheckSendMessageServiceTest
 * 
 * @author r12
 * @version 1.0
 * @date 2018.08.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EquipmentCheckSendMessageServiceTest {

    @Test
    public void testCheckEquipmentAndSendMessageAlarm() {
    }
    
    @Test
    public void testCheckEquipmentAndSendMessageOffline() {
    }

    @Autowired
    private EquipmentCheckSendMessageService equipmentCheckSendMessageService;
}
