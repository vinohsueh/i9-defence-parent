package i9.defence.platform.model;

import java.util.Date;

/**
 * 设备监控统计
* @ClassName: EquipmentMonitor 
* @Description: TODO
* @author luobo
* @date 2018年1月8日 下午5:15:14 
*
 */
public class EquipmentMonitor {
    private Integer id;
    
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
    
    /**
     * 设备表
     */
    private Integer eqId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.eqState = eqState == null ? null : eqState.trim();
    }

    public Integer getEqId() {
        return eqId;
    }

    public void setEqId(Integer eqId) {
        this.eqId = eqId;
    }
}