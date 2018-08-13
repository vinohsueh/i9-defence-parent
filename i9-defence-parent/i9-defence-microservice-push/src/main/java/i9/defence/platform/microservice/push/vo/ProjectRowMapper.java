package i9.defence.platform.microservice.push.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @ClassName: ProjectRowMapper 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年8月13日 上午10:40:35
 */
public class ProjectRowMapper  implements RowMapper<ProjectInfoDto>{

	@Override
	public ProjectInfoDto mapRow(ResultSet rs, int i) throws SQLException {
		ProjectInfoDto deviceDto = new ProjectInfoDto();
		deviceDto.setId(rs.getInt("id"));
		deviceDto.setProjectName(rs.getString("projectName"));
		deviceDto.setRecipientphones(rs.getString("recipientphones"));
		deviceDto.setRecipients(rs.getString("recipients"));
		deviceDto.setSendStatus(rs.getInt("sendStatus"));
		deviceDto.setSendType(rs.getString("sendType")); 
		return deviceDto;
	}

}
