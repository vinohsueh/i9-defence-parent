package i9.defence.platform.datapush.entity;

@Entity
@Component
public class User {

    public User() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 创建时间
     */
    private Date createTime;

}