package i9.defence.platform.datapush.dto;

public class DeviceAttributeDto implements java.io.Serializable {

    private static final long serialVersionUID = -2190524822907272096L;

    public DeviceAttributeDto() {
    }

    private String attributeName;

    private String datastream;

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
