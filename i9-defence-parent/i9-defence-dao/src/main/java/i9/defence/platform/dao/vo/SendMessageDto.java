package i9.defence.platform.dao.vo;

import i9.defence.platform.utils.AliyunSMSEnum;

/**
 * 短信群发dto
 * @ClassName: SendInfoDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年7月26日 上午11:28:34
 */
public class SendMessageDto {
	
	/**
	 * 客户id
	 */
	private  Integer[] ClientIdList;
	
	/**
	 * 短信模板号
	 */
	private  String templateNum;
	
	private AliyunSMSEnum aliyunSMSEnum;
	
	public AliyunSMSEnum getAliyunSMSEnum() {
		return aliyunSMSEnum;
	}

	public void setAliyunSMSEnum(AliyunSMSEnum aliyunSMSEnum) {
		this.aliyunSMSEnum = aliyunSMSEnum;
	}

	public Integer[] getClientIdList() {
		return ClientIdList;
	}

	public void setClientIdList(Integer[] clientIdList) {
		ClientIdList = clientIdList;
	}

	public String getTemplateNum() {
		return templateNum;
	}

	public void setTemplateNum(String templateNum) {
		this.templateNum = templateNum;
	}
	
	

}
