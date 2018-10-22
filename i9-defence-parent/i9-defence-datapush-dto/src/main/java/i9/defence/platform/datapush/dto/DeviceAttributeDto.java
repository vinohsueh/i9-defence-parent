package i9.defence.platform.datapush.dto;

/**
 * 设备属性DTO
 * 
 * @author R12
 * @date 2018年10月22日 14:01:40
 */
public class DeviceAttributeDto implements java.io.Serializable {

    private static final long serialVersionUID = -2190524822907272096L;

    public DeviceAttributeDto() {
    }

    /**
     * 属性名称
     */
    private String attributeName;

    /**
     * 数据流
     */
    private String datastream;

    /**
     * 值
     */
    private String value;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
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
}
