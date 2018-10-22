package i9.defence.platform.datapush.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 设备数据历史
 * 
 * @author R12
 * @date 2018年10月22日 14:15:13
 */
@Entity
@Table(name = "tb_device_data_his")
public class DeviceDataHis implements java.io.Serializable {

    private static final long serialVersionUID = 6726021046709629965L;

    public DeviceDataHis() {
    }

    /**
     * ID
     */
    private String id;

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 数据流
     */
    private String datastream;

    /**
     * 值
     */
    private String value;

    /**
     * 提交日期
     */
    private Date createDate;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDatastream() {
        return datastream;
    }

    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
