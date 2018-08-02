package i9.defence.platform.model;

/**
 * 短信日志
 * @ClassName: MessageLog 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月1日 下午4:59:46
 */
public class MessageLog {
    private Integer id;

    /**
     * 短信模板id
     */
    private String templateNum;

    /**
     * 发送状态 0：成功，1：失败
     */
    private Integer sendStatus;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 接收信息的手机号
     */
    private String phones;

    /**
     * 收信人姓名
     */
    private String clientNames;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 若成功，返回"ok",失败返回失败的信息
     */
    private String sendResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(String templateNum) {
        this.templateNum = templateNum == null ? null : templateNum.trim();
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones == null ? null : phones.trim();
    }

    public String getClientNames() {
        return clientNames;
    }

    public void setClientNames(String clientNames) {
        this.clientNames = clientNames == null ? null : clientNames.trim();
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult == null ? null : sendResult.trim();
    }
}