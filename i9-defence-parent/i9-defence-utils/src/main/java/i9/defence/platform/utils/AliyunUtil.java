package i9.defence.platform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @ClassName: AliyunUtil
 * @author: luobo
 * @date: 2018年7月26日 上午10:27:57
 */
public class AliyunUtil {

    // 产品名称:云通信短信API产品,开发者无需替换
    private static String product;
    // 产品域名,开发者无需替换
    private static String domain;
    
    private static String accessKeyId;
    private static String accessKeySecret;

    static {
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sms.properties");
        try {
            properties.load(inputStream);
            product = properties.getProperty("aliyun.sms.product");
            domain = properties.getProperty("aliyun.sms.domain");
            accessKeyId = properties.getProperty("aliyun.sms.accessKeyId");
            accessKeySecret = properties.getProperty("aliyun.sms.accessKeySecret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String sendInfo(AliyunSMSEnum aliyunSMSEnum, String phones, String clientNames, String SignNames) {
        return sendInfo(aliyunSMSEnum.getTemplateNum(), phones, clientNames, SignNames);
    }

    /**
     * 群发通知
     * 
     * @param <T>
     * @Title: sendInfo
     * @param templateNum
     * @param ClientList
     * @return
     */
    public static String sendInfo(String templateNum, String phones, String clientNames, String SignNames) {
        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e1) {
            throw new BusinessException(ErrorCode.ALIYUN_NOT_ENOUGH_ERROR, "初始化错误", e1.getMessage());
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        request.setPhoneNumberJson(phones);
        // request.setPhoneNumberJson("[\"15900000000\",\"17600000000\"]");
        // 必填:短信签名-可在短信控制台中找到
        request.setSignNameJson(SignNames);
        // request.setSignNameJson("[\"不好先生\",\"不好先生\"]");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateNum);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParamJson(clientNames);
        // request.setTemplateParamJson("{name:"普豪\"},{\"name\":\"建普\"}]");
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        try {
            SendBatchSmsResponse response = acsClient.getAcsResponse(request);
            return response.getCode();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.ALIYUN_NOT_ENOUGH_ERROR, "客户端错误", e.getMessage());
        }
    }
}
