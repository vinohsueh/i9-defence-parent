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
    private Integer equipId;
    
    
    /**
     * 隐患类型Id
     */
    private Integer HiddenDangerId;
    
    /**
     * 一对一隐患类型
     */
    private HiddenDanger hiddenDanger;
    
    
    
    public Integer getHiddenDangerId() {
		return HiddenDangerId;
	}

	public void setHiddenDangerId(Integer hiddenDangerId) {
		HiddenDangerId = hiddenDangerId;
	}

	public HiddenDanger getHiddenDanger() {
		return hiddenDanger;
	}

	public void setHiddenDanger(HiddenDanger hiddenDanger) {
		this.hiddenDanger = hiddenDanger;
	}

	public Integer getEquipId() {
		return equipId;
	}

	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
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