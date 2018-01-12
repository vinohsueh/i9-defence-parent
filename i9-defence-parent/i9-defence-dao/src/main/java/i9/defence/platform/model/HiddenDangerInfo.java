package i9.defence.platform.model;

/**
 * 隐患具体信息Model
* @ClassName: HiddenDangerInfo 
* @Description: TODO
* @author luobo
* @date 2018年1月9日 下午4:13:16 
*
 */
public class HiddenDangerInfo {
    private Integer id;
    
    /**
     * 阀值类型(0:隐患阀值 1:报警阀值)
     */
    private Integer type;
    
    /**
     * 隐患名称ID
     */
    private Integer hid;

    /**
     * 电压/电流：最高/最低值
     */
    private String name;
    
    /**
     * electricity(电流)/voltage(电压)
     */
    private String keyName;
    
    /**
     * 最高/最低值
     */
    private String maxOrMin;
    
    /**
     * 电压/电流值
     */
    private String value;
    
    /**
     * 隐患名称（一对一关系）
     */
    private HiddenDanger hiddenDanger;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

  

    public HiddenDanger getHiddenDanger() {
		return hiddenDanger;
	}

	public void setHiddenDanger(HiddenDanger hiddenDanger) {
		this.hiddenDanger = hiddenDanger;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public String getMaxOrMin() {
        return maxOrMin;
    }

    public void setMaxOrMin(String maxOrMin) {
        this.maxOrMin = maxOrMin == null ? null : maxOrMin.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}
    
}