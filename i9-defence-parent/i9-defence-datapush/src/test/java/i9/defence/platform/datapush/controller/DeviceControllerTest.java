package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.ServerRun;
import i9.defence.platform.datapush.utils.HttpResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerRun.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @LocalServerPort
    private int port;

    private URL baseURL;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        this.baseURL = new URL(url);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testDeleteDevice() {
        HttpResult<?> httpResult = this.restTemplate.postForObject(baseURL + "baseAPI/deleteDevice.sapi", "aaa",  HttpResult.class);
        System.out.println(httpResult.getMessage());
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }
}
