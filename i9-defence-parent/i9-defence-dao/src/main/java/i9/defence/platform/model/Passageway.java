package i9.defence.platform.model;

/**
 * 通道实体类
 * @ClassName: Passageway 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年3月27日 上午11:33:06
 */
public class Passageway   {
	

    /**
     * 通道顺序
     */
    private Integer channel;
    
	/**
	 * 通道名称
	 */
    private String name;

    /**
     * 设备Id
     */
    private String systemId;
    
    
    /**
     * 隐患类型Id
     */
    private Integer hiddenDangerId;
    
    /**
     * 一对一隐患类型
     */
    private HiddenDanger hiddenDanger;
    
    

	public Integer getHiddenDangerId() {
		return hiddenDangerId;
	}

	public void setHiddenDangerId(Integer hiddenDangerId) {
		this.hiddenDangerId = hiddenDangerId;
	}

	public HiddenDanger getHiddenDanger() {
		return hiddenDanger;
	}

	public void setHiddenDanger(HiddenDanger hiddenDanger) {
		this.hiddenDanger = hiddenDanger;
	}

	

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}