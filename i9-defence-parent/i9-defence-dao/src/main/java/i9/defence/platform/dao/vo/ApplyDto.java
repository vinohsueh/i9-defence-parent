package i9.defence.platform.dao.vo;

/**
 * ApplyDto
 * @ClassName: ApplyDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月18日 上午9:32:51
 */
public class ApplyDto {
	 
	    /**
	     * 申请类型(0:删除项目,1:删除设备)
	     */
	    private Integer type;
	    
	    /**
	     * 申请状态(0:未处理,1:已处理)
	     */
	    private Integer state;
	    
	    /**
	     * 类型
	     */
	    private Integer destriId;
	    
		//数据库ORDER BY 排序
		private String orderByClause;
	    

		public String getOrderByClause() {
			return orderByClause;
		}


		public void setOrderByClause(String orderByClause) {
			this.orderByClause = orderByClause;
		}


		public Integer getType() {
			return type;
		}


		public void setType(Integer type) {
			this.type = type;
		}


		public Integer getState() {
			return state;
		}


		public void setState(Integer state) {
			this.state = state;
		}

		public Integer getDestriId() {
			return destriId;
		}


		public void setDestriId(Integer destriId) {
			this.destriId = destriId;
		}
	    
	    
	    
}
