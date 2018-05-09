package i9.defence.platform.microservice.push.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @Resource
    private JdbcTemplate primaryJdbcTemplate;

    public List<Map<String, Object>> getTestList() {
        String sql = "select * from t_client";
        return primaryJdbcTemplate.queryForList(sql);
    }
}
