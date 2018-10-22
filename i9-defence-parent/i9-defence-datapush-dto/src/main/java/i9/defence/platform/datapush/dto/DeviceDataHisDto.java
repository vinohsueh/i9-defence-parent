package i9.defence.platform.datapush.dto;

import java.util.Date;

/**
 * 设备上传数据历史
 * 
 * @author R12
 * @date 2018年10月22日 14:02:26
 */
public class DeviceDataHisDto implements java.io.Serializable {

    private static final long serialVersionUID = 478510206348154489L;

    /**
     * 值
     */
    private String value;

    /**
     * 上传时间
     */
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
