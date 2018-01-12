package i9.defence.platform.dao.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 隐患设备Dto
* @ClassName: HiddenDangerDto 
* @Description: TODO
* @author luobo
* @date 2018年1月10日 上午10:28:17 
*
 */
public class HiddenDangerDto extends PageListDto{
	
	/**
	 * 隐患名称
	 */
    @NotBlank(message="隐患名称不能为空")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
	
}
