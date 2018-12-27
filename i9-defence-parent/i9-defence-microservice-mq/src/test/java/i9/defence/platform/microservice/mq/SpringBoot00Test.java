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
 * 
 * @author lby
 * @version
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SpringBoot00Test {

    @Test
    public void test() {
        String textMessage = "{\"systemId\":\"000000000001\",\"deviceAddress\":\"1000FFE1\",\"unit\":16,\"loop\":0,\"dataList\":[{\"datetime\":\"2018-05-25#19:48:26\",\"len\":2,\"data\":\"0003\",\"channel\":0,\"source\":0,\"type\":2,\"value\":3},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":2,\"data\":\"01FF\",\"channel\":1,\"source\":0,\"type\":2,\"value\":511},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":2,\"data\":\"0207\",\"channel\":2,\"source\":0,\"type\":2,\"value\":519},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":2,\"data\":\"0220\",\"channel\":3,\"source\":0,\"type\":2,\"value\":544},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"0000016B\",\"channel\":4,\"source\":0,\"type\":5,\"value\":36.3},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000009\",\"channel\":5,\"source\":0,\"type\":5,\"value\":0.9},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000024\",\"channel\":6,\"source\":0,\"type\":5,\"value\":3.6},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000024\",\"channel\":7,\"source\":0,\"type\":5,\"value\":3.6},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000000\",\"channel\":8,\"source\":0,\"type\":0,\"value\":\"00000000\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000005\",\"channel\":9,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000005\",\"channel\":10,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000005\",\"channel\":11,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000000\",\"channel\":12,\"source\":0,\"type\":0,\"value\":\"00000000\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000005\",\"channel\":13,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000005\",\"channel\":14,\"source\":0,\"type\":0,\"value\":\"00000005\"},{\"datetime\":\"2018-05-25#19:48:26\",\"len\":4,\"data\":\"00000005\",\"channel\":15,\"source\":0,\"type\":0,\"value\":\"00000005\"}],\"systemType\":\"0002\",\"source\":3,\"dataLen\":200}";
        try {
            upStreamDecodeService.saveUpStreamDecode(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private UpStreamDecodeService upStreamDecodeService;
}
