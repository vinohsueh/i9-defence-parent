package i9.defence.platform.enums;

/**
 * aliyun短信回执code
 * @ClassName: AliyunCodeTypeEnum 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月3日 上午10:36:52
 */
public enum AliyunCodeTypeEnum {
	
	SUCCESS("ok","请求成功"),
	PERMISSION_DENY("isp.RAM_PERMISSION_DENY","RAM权限DENY"),
	OUTOF_SERVICE("isv.OUT_OF_SERVICE","业务停机"),
	PRODUCT_UN_SUBSCRIPT("isv.PRODUCT_UN_SUBSCRIPT	","未开通云通信产品的阿里云客户"),
	PRODUCT_UNSUBSCRIBE("isv.PRODUCT_UNSUBSCRIBE","产品未开通"),
	ACCOUNT_NOT_EXISTS("isv.ACCOUNT_NOT_EXISTS","账户不存在"),
	ACCOUNT_ABNORMAL("isv.ACCOUNT_ABNORMAL","账户异常"),
	SMS_TEMPLATE_ILLEGAL("isv.SMS_TEMPLATE_ILLEGAL","短信模板不合法"),
	SMS_SIGNATURE_ILLEGAL("isv.SMS_SIGNATURE_ILLEGAL","短信签名不合法"),
	INVALID_PARAMETERS("isv.INVALID_PARAMETERS","参数异常"),
	SYSTEM_ERROR("isp.SYSTEM_ERROR","系统错误"),
	MOBILE_NUMBER_ILLEGAL("isv.MOBILE_NUMBER_ILLEGAL","非法手机号"),
	MOBILE_COUNT_OVER_LIMIT("isv.MOBILE_COUNT_OVER_LIMIT","手机号码数量超过限制"),
	TEMPLATE_MISSING_PARAMETERS("isv.TEMPLATE_MISSING_PARAMETERS","模板缺少变量"),
	BUSINESS_LIMIT_CONTROL("isv.BUSINESS_LIMIT_CONTROL","业务限流"),
	INVALID_JSON_PARAM("isv.INVALID_JSON_PARAM","JSON参数不合法，只接受字符串值"),
	BLACK_KEY_CONTROL_LIMIT("isv.BLACK_KEY_CONTROL_LIMIT","黑名单管控"),
	PARAM_LENGTH_LIMIT("isv.PARAM_LENGTH_LIMIT","参数超出长度限制"),
	PARAM_NOT_SUPPORT_URL("isv.PARAM_NOT_SUPPORT_URL","不支持URL"),
	TEMPLATE_PARAMS_ILLEGAL("isv.TEMPLATE_PARAMS_ILLEGAL","模板变量里包含非法关键字"),
	AMOUNT_NOT_ENOUGH("isv.AMOUNT_NOT_ENOUGH","账户余额不足");
	
	private String key;
	
	private String value;

	private AliyunCodeTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValueByKey(String key) {
		for(AliyunCodeTypeEnum e:values()) {
			if(e.getKey().equals(key)) {
				return e.getValue();
			}
		}
		return null;
	}
}
