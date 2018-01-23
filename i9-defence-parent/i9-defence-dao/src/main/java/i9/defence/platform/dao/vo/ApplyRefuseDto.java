package i9.defence.platform.dao.vo;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * 创建时间：2018年1月22日 下午1:44:00
 * @author  lby
 * @version  
 * 
 */
public class ApplyRefuseDto {
    
    @NotEmpty(message = "至少选择一个")
    private List<Integer> ids;
    
    @NotBlank(message="拒绝理由不能为空")
    @Size(max=200,message="拒绝理由在200字以内")
    private String content;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
