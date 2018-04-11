package i9.defence.platform.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import i9.defence.platform.utils.StringUtil;

/**
* @author : JiaCe
* @version ：2018年1月11日下午2:59:09
* 客户
*/
public class Client {
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    @Length(max=30,min=2,message="用户名长度在2-20之间")
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 地域
     */
    @NotBlank(message="地域不能为空")
    private String territory;
    /**
     * 电话
     */
    @NotBlank(message="电话号码不能为空")
    @Pattern(regexp="^1[3|4|5|6|7|8][0-9]\\d{8}$",message="请输入正确的手机号")
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    @Pattern(regexp="\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}",message="请输入正确的邮箱格式")
    private String mail;
    /**
     * 关联的项目名称集合
     */
    private List<String> project;
    /**
     * 创建人
     */
    private Integer createId;
    /**
     * 关联的项目名称
     */
    private String projectName;
    
    
    public List<String> getProject() {
		return project;
	}

	public void setProject(List<String> project) {
		this.project = project;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory == null ? null : territory.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }
    public String getCreateTimeString(){
        return StringUtil.dateToStringWithoutTime(createTime);
    }
}