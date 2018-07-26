package i9.defence.platform.microservice.push.vo;

import java.util.Date;

/** 
* 创建时间：2018年5月22日 下午2:47:57
* @author  lby
* @version  
* 
*/
public class ChannelData {
	
	private int channel;
	
	private String code;
	
	private int loop;
	
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLoop() {
		return loop;
	}

	public void setLoop(int loop) {
		this.loop = loop;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
