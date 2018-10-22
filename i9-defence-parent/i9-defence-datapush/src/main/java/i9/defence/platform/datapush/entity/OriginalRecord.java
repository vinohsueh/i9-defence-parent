package i9.defence.platform.datapush.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 数据原始记录
 * 
 * @author R12
 * @date 2018年10月22日 14:19:06
 */
@Entity
@Table(name = "tb_original_record")
public class OriginalRecord implements java.io.Serializable {

    private static final long serialVersionUID = 6156013847848658011L;

    public OriginalRecord() {
    }

    private String id;

    private Date submitDate;

    private String message;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
