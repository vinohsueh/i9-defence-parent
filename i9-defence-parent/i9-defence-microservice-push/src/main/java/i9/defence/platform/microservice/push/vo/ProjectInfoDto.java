package i9.defence.platform.microservice.push.vo;

/**
 * 项目dto
 * @ClassName: ProjectInfoDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月13日 上午10:33:56
 */
public class ProjectInfoDto {
	
	private int id;
	
	/**
	 * 项目名
	 */
	private String projectName;
	
	/**
	 * 发送短信状态 0:不发送 1:发送
	 */
	private int sendStatus;
	
	/**
	 * 发送类型 0:报警 1：离线 2：隐患
	 */
	private String sendType;
	
	/**
	 * 收件人手机号
	 */
	private String recipientphones;
	
	/**
	 * 收件人
	 */
	private String recipients;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getRecipientphones() {
		return recipientphones;
	}

	public void setRecipientphones(String recipientphones) {
		this.recipientphones = recipientphones;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
}
