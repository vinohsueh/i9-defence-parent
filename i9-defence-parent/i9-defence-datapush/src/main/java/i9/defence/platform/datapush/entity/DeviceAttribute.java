package i9.defence.platform.datapush.entity;

import i9.defence.platform.datapush.utils.StringHelper;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_device_attribute")
public class DeviceAttribute implements java.io.Serializable {

    private static final long serialVersionUID = -6544439968995923403L;

    public DeviceAttribute() {
    }

    public DeviceAttribute(String deviceId, String datastream) {
        this.id = StringHelper.randomUUIDStr();
        this.deviceId = deviceId;
        this.datastream = datastream;
        this.createDate = new Date();
    }

    private String id;

    private String deviceId;

    private String datastream;

    private String value;

    private Date createDate;

    private Date updateDate;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
