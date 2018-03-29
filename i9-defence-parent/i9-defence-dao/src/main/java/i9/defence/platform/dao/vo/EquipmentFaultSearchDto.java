package i9.defence.platform.dao.vo; 
/** 
* @author user: jiace
* @version creatTime：2018年3月29日 上午11:12:21 
* 
*/
public class EquipmentFaultSearchDto extends PageListDto{

	 /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
 