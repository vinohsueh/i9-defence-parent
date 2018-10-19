package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.utils.HttpResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class DeviceControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        HttpResult<?> httpResult = this.restTemplate.getForObject(
                "http://localhost:9999/baseAPI/deleteDevice.sapi", HttpResult.class);
        System.out.println(httpResult.getMessage());
    }
}
