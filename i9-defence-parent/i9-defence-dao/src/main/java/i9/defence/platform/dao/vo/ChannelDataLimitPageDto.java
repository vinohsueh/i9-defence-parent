package i9.defence.platform.dao.vo;

import java.util.Date;

/**
 * 分页查询通道数据返回值
 * @ClassName: ChannelDataLimitPageDto 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年4月14日 上午10:28:10
 */
public class ChannelDataLimitPageDto {
	
		private Integer id;

	    private String systemId;

	    private Integer channel;

	    private Date dateTime;

	    private Integer type;

	    private String value;
	    
	    private String systemType;
	    
	    private Integer dealStatus;

		private String dealDetail;
		
		private String deviceId;
		
		private String deviceAddress;

		/**
		 * hiddenDanger表数据
		 */
		 /**
	     * 隐患类型名称
	     */
	    private String name;

	    /**
	     * 隐患阀值最大值
	     */
	    private Double hiddenMax;

	    /**
	     * 隐患阀值最小值
	     */
	    private Double hiddenMin;

	    /**
	     * 报警阀值最大值
	     */
	    private Double warningMax;

	    /**
	     * 报警阀值最小值
	     */
	    private Double warningMin;

		
		
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getHiddenMax() {
			return hiddenMax;
		}

		public void setHiddenMax(Double hiddenMax) {
			this.hiddenMax = hiddenMax;
		}

		public Double getHiddenMin() {
			return hiddenMin;
		}

		public void setHiddenMin(Double hiddenMin) {
			this.hiddenMin = hiddenMin;
		}

		public Double getWarningMax() {
			return warningMax;
		}

		public void setWarningMax(Double warningMax) {
			this.warningMax = warningMax;
		}

		public Double getWarningMin() {
			return warningMin;
		}

		public void setWarningMin(Double warningMin) {
			this.warningMin = warningMin;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public Date getDateTime() {
			return dateTime;
		}

		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getSystemType() {
			return systemType;
		}

		public void setSystemType(String systemType) {
			this.systemType = systemType;
		}

		public Integer getDealStatus() {
			return dealStatus;
		}

		public void setDealStatus(Integer dealStatus) {
			this.dealStatus = dealStatus;
		}

		public String getDealDetail() {
			return dealDetail;
		}

		public void setDealDetail(String dealDetail) {
			this.dealDetail = dealDetail;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getDeviceAddress() {
			return deviceAddress;
		}

		public void setDeviceAddress(String deviceAddress) {
			this.deviceAddress = deviceAddress;
		} 
		
		/*public String getErrorName () {
			try {
				if (type == DataTypeEnum.ERROR.getId()) {
					return "故障";
				}else if (type == DataTypeEnum.FLOAT.getId()) {
					double  cValue  = 0;
					if (!value.equals("0.0")) {
						cValue = 0;
					}else{
						cValue = Double.valueOf(value);
					}
					if (Double.valueOf(value) >= warningMax || Double.valueOf(value) <= warningMin) {
						return "报警";
					}else if ((Double.valueOf(value) < hiddenMin && Double.valueOf(value) > warningMin) || (Double.valueOf(value) > hiddenMax && Double.valueOf(value) < warningMax)){
						return "隐患";
					}else{
						return "1";
					}
				}else{
					return "1";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
			
			
		}*/
		
}
