package i9.defence.platform.datapush.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备组属性数据流映射
 * 
 * @author R12
 * @date 2018年10月22日 14:16:49
 */
@Entity
@Table(name = "tb_device_group_attribute")
public class DeviceGroupAttribute implements java.io.Serializable {

    public DeviceGroupAttribute() {
    }

    private static final long serialVersionUID = 4435215125773820291L;

    /**
     * ID
     */
    private String id;

    /**
     * 设备组编号
     */
    private String deviceGroupId;

    /**
     * 属性名
     */
    private String attribute;

    /**
     * 数据流
     */
    private String datastream;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getDatastream() {
        return datastream;
    }

    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }
}
