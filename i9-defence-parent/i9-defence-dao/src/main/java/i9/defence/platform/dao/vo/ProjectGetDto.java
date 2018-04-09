package i9.defence.platform.dao.vo;

import java.io.Serializable;

public class ProjectGetDto implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4024777650254814481L;

	private Integer projectId;
	 
	 private Integer distributorId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}
	 
	 
}
