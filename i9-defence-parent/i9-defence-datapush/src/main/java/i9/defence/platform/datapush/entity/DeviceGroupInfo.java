package i9.defence.platform.datapush.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备组信息
 * 
 * @author R12
 * @date 2018年10月22日 14:17:07
 */
@Entity
@Table(name = "tb_device_group_info")
public class DeviceGroupInfo implements java.io.Serializable {

    public DeviceGroupInfo() {
    }

    private static final long serialVersionUID = 7926474731743814659L;

    /**
     * ID
     */
    private String id;

    /**
     * 设备组名
     */
    private String groupName;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
