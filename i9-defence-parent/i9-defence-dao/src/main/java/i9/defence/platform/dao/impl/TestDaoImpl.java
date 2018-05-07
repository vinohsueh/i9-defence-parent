package i9.defence.platform.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.TestDao;
import i9.defence.platform.dao.vo.Demo;

/** 
* 创建时间：2018年5月7日 上午10:14:20
* @author  lby
* @version  
* 
*/
@Repository
public class TestDaoImpl implements TestDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Demo> getList() {
		 String sql = "select *from device_record_tables";
	        return (List<Demo>) jdbcTemplate.query(sql, new RowMapper<Demo>(){

				@Override
				public Demo mapRow(ResultSet rs, int rowNum) throws SQLException {
					Demo demo = new Demo();
	                demo.setId(rs.getInt("id"));
	                demo.setTable_name(rs.getString("table_name"));;
	                return demo;
				}
	        });
	}

}
