package i9.defence.platform.datapush.controller;

import i9.defence.platform.datapush.ServerRun;
import i9.defence.platform.datapush.dto.DeviceAttributeDto;
import i9.defence.platform.datapush.dto.DeviceDataHisDto;
import i9.defence.platform.datapush.dto.DeviceDetailsDto;
import i9.defence.platform.datapush.dto.DeviceInfoDto;
import i9.defence.platform.datapush.dto.OriginalRecordDto;
import i9.defence.platform.datapush.utils.HttpResult;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * 设备对外测试接口调试类
 * 
 * @author R12
 * @date 2018年10月23日 09:44:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerRun.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @LocalServerPort
    private int serverPort;

    private URL baseURL;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", serverPort);
        this.baseURL = new URL(url);
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试删除设备
     */
    @Test
    public void testDeleteDevice() {
        final String deviceId = "aa";
        HttpResult<?> httpResult = this.restTemplate.postForObject(baseURL + "baseAPI/deleteDevice.sapi", deviceId,
                HttpResult.class);
        System.out.println(httpResult.getMessage());
    }

    /**
     * 测试添加设备
     */
    @Test
    public void testAddDevice() {
        final String deviceId = "aa";
        HttpResult<?> httpResult = this.restTemplate.postForObject(baseURL + "baseAPI/addDevice.sapi", deviceId,
                HttpResult.class);
        System.out.println(httpResult.getMessage());
    }

    /**
     * 测试设备同步数据
     */
    @Test
    public void testRefreshDevice() {
        final String deviceId = "aa";
        HttpResult<?> httpResult = this.restTemplate.postForObject(baseURL + "baseAPI/refreshDevice.sapi", deviceId,
                HttpResult.class);
        System.out.println(httpResult.getMessage());
    }

    /**
     * 测试查询设备详情
     */
    @Test
    public void testDeviceDetails() {
        ParameterizedTypeReference<HttpResult<DeviceInfoDto>> typeRef = new ParameterizedTypeReference<HttpResult<DeviceInfoDto>>() {
        };

        final String jsonPost = "0c909a2c-5ac7-48df-92ef-b1c2a9516a83";
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPost);
        ResponseEntity<HttpResult<DeviceInfoDto>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/deviceDetails.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<DeviceInfoDto> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        DeviceInfoDto deviceInfoDto = (DeviceInfoDto) httpResult.getRe();
        JSONObject jsonObject = new JSONObject(deviceInfoDto);
        System.out.println("re : " + jsonObject.toString());
    }

    /**
     * 测试查询设备列表
     */
    @Test
    public void testDeviceList() {
        ParameterizedTypeReference<HttpResult<List<DeviceInfoDto>>> typeRef = new ParameterizedTypeReference<HttpResult<List<DeviceInfoDto>>>() {
        };

        HttpEntity<String> requestEntity = new HttpEntity<String>("");
        ResponseEntity<HttpResult<List<DeviceInfoDto>>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/deviceList.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<List<DeviceInfoDto>> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        for (DeviceInfoDto deviceInfoDto : httpResult.getRe()) {
            JSONObject jsonObject = new JSONObject(deviceInfoDto);
            System.out.println("re : " + jsonObject.toString());
        }
    }

    /**
     * 测试查询设备属性
     */
    @Test
    public void testDeviceAttribute() {
        ParameterizedTypeReference<HttpResult<List<DeviceAttributeDto>>> typeRef = new ParameterizedTypeReference<HttpResult<List<DeviceAttributeDto>>>() {
        };

        final String jsonPost = "0c909a2c-5ac7-48df-92ef-b1c2a9516a83";
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonPost);
        ResponseEntity<HttpResult<List<DeviceAttributeDto>>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/deviceAttribute.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<List<DeviceAttributeDto>> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        for (DeviceAttributeDto deviceAttributeDto : httpResult.getRe()) {
            JSONObject jsonObject = new JSONObject(deviceAttributeDto);
            System.out.println("re : " + jsonObject.toString());
        }
    }

    /**
     * 测试通过设备id列表查询设备列表
     */
    @Test
    public void testSearchDeviceList() {
        ParameterizedTypeReference<HttpResult<List<DeviceInfoDto>>> typeRef = new ParameterizedTypeReference<HttpResult<List<DeviceInfoDto>>>() {
        };

        List<String> ids = new ArrayList<String>();
        ids.add("0c909a2c-5ac7-48df-92ef-b1c2a9516a83");

        HttpEntity<List<String>> requestEntity = new HttpEntity<List<String>>(ids);
        ResponseEntity<HttpResult<List<DeviceInfoDto>>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/searchDeviceList.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<List<DeviceInfoDto>> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        for (DeviceInfoDto deviceInfoDto : httpResult.getRe()) {
            JSONObject jsonObject = new JSONObject(deviceInfoDto);
            System.out.println("re : " + jsonObject.toString());
        }
    }

    /**
     * 测试设备数据点历史记录
     */
    @Test
    public void testDeviceDatapoint() {
        ParameterizedTypeReference<HttpResult<List<DeviceDataHisDto>>> typeRef = new ParameterizedTypeReference<HttpResult<List<DeviceDataHisDto>>>() {
        };

        HashMap<String, Object> params = new HashMap<String, Object>(4);
        // 设备编号
        params.put("id", "0c909a2c-5ac7-48df-92ef-b1c2a9516a83");
        // 数据点
        params.put("datastream", "3_0_21");
        // 开始时间
        params.put("startDate", "2018-01-01 00:00:00");
        // 结束时间
        params.put("endDate", "2018-12-20 00:00:00");

        JSONObject jsonObject = new JSONObject(params);
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonObject.toString());
        ResponseEntity<HttpResult<List<DeviceDataHisDto>>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/deviceDatapoint.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<List<DeviceDataHisDto>> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        for (DeviceDataHisDto deviceDataHisDto : httpResult.getRe()) {
            jsonObject = new JSONObject(deviceDataHisDto);
            System.out.println("re : " + jsonObject.toString());
        }
    }

    /**
     * 测试获取原始数据记录
     */
    @Test
    public void testOriginalRecordList() {
        ParameterizedTypeReference<HttpResult<List<OriginalRecordDto>>> typeRef = new ParameterizedTypeReference<HttpResult<List<OriginalRecordDto>>>() {
        };

        HashMap<String, Object> params = new HashMap<String, Object>(2);
        // 开始时间
        params.put("startDate", "2018-10-01 00:00:00");
        // 结束时间
        params.put("endDate", "2018-12-20 00:00:00");

        JSONObject jsonObject = new JSONObject(params);
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonObject.toString());
        ResponseEntity<HttpResult<List<OriginalRecordDto>>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/originalRecordList.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<List<OriginalRecordDto>> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        for (OriginalRecordDto originalRecordDto : httpResult.getRe()) {
            jsonObject = new JSONObject(originalRecordDto);
            System.out.println("re : " + jsonObject.toString());
        }
    }

    /**
     * 测试查询设备详情及属性信息
     */
    @Test
    public void testDeviceDetailsAndAttribute() {
        ParameterizedTypeReference<HttpResult<DeviceDetailsDto>> typeRef = new ParameterizedTypeReference<HttpResult<DeviceDetailsDto>>() {
        };
        HttpEntity<String> requestEntity = new HttpEntity<String>("0c909a2c-5ac7-48df-92ef-b1c2a9516a83");
        ResponseEntity<HttpResult<DeviceDetailsDto>> responseEntity = this.restTemplate.exchange(baseURL
                + "baseAPI/deviceDetailsAndAttribute.sapi", HttpMethod.POST, requestEntity, typeRef);

        HttpResult<DeviceDetailsDto> httpResult = responseEntity.getBody();
        System.out.println("code : " + httpResult.getCode() + ", message :" + httpResult.getMessage());

        DeviceDetailsDto deviceDetailsDto = httpResult.getRe();

        JSONObject jsonObject = new JSONObject(deviceDetailsDto.getDeviceInfoDto());
        System.out.println("re : " + jsonObject.toString());

        for (DeviceAttributeDto deviceAttributeDto : deviceDetailsDto.getDeviceAttributeDtos()) {
            jsonObject = new JSONObject(deviceAttributeDto);
            System.out.println("re : " + jsonObject.toString());
        }
    }
}
