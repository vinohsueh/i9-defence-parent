package i9.defence.platform.dao.vo;

import java.sql.Date;

/**
 * 设备实时监控Dto
* @ClassName: EquipmentMonitorDto 
* @Description: TODO
* @author luobo
* @date 2018年1月9日 上午9:53:10 
*
 */
public class EquipmentMonitorDto {
	
	/**
	 * 电压
	 */
	private Double voltage;
	
	/**
	 * 电流
	 */
	private Double electricity;
	
	/**
	 * 设备实时时间
	 */
	private Date realtime;
	
	/**
	 * 设备状态
	 */
	private String eqState;

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Double getElectricity() {
		return electricity;
	}

	public void setElectricity(Double electricity) {
		this.electricity = electricity;
	}

	public Date getRealtime() {
		return realtime;
	}

	public void setRealtime(Date realtime) {
		this.realtime = realtime;
	}

	public String getEqState() {
		return eqState;
	}

	public void setEqState(String eqState) {
		this.eqState = eqState;
	}
	
}
