package i9.defence.platform.datapush.dto;

import java.util.Date;

public class DeviceDataHisDto implements java.io.Serializable {

    private static final long serialVersionUID = 478510206348154489L;

    private String value;

    private Date createDate;

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
