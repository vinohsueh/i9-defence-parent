package i9.defence.platform.service;

/**
 * 检测设备，自动发送短信
 * @ClassName: automaticSendMessageService 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月9日 上午11:11:41
 */
public interface AutomaticSendMessageService {
	
	/**
	 * 自动发送短信
	* @Title: automaticSendMessage 
	* @Description: TODO
	* @param deviceId
	* @param EquipmentType
	 */
	void AutomaticSendMessage(String deviceId,Integer EquipmentType);
}
