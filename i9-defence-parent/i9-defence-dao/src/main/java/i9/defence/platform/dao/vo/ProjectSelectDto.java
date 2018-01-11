package i9.defence.platform.dao.vo;

import java.io.Serializable;
/**
 * 新建设备时候选择那个项目时候用
 * @author 姜哲
 * @create 2018年1月8日
 */
public class ProjectSelectDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6153449960742385461L;
	//项目主键
    private Integer id;
    //项目名称
    private String projectName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
    
    
}
