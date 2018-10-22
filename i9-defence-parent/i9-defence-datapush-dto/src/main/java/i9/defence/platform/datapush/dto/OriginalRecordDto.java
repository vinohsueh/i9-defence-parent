package i9.defence.platform.datapush.dto;

import java.util.Date;

/**
 * 数据原始记录
 * 
 * @author R12
 * @date 2018年10月22日 14:19:06
 */
public class OriginalRecordDto implements java.io.Serializable {

    private static final long serialVersionUID = -2367398305489910964L;

    public OriginalRecordDto() {
    }

    private Date submitDate;

    private String message;

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
