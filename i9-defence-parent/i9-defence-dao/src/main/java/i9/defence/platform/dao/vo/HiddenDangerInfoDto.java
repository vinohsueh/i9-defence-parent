package i9.defence.platform.dao.vo;

import i9.defence.platform.model.HiddenDanger;

/**
 * 隐患提醒类型具体信息Dto
 * @ClassName: HiddenDangerInfoInfoDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月10日 下午2:39:37
 */
public class HiddenDangerInfoDto  extends PageListDto{
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
     * 电压/电流值
     */
    private String value;
    
    
    /**
     * 隐患名称（一对一关系）
     */
    private HiddenDanger hiddenDanger;


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getHid() {
		return hid;
	}


	public void setHid(Integer hid) {
		this.hid = hid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public HiddenDanger getHiddenDanger() {
		return hiddenDanger;
	}


	public void setHiddenDanger(HiddenDanger hiddenDanger) {
		this.hiddenDanger = hiddenDanger;
	}
    
	
}
