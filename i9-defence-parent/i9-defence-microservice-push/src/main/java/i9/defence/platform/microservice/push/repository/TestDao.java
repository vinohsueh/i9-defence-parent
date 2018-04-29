package i9.defence.platform.microservice.push.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTestList() {
        String sql = "select * from t_client";
        return jdbcTemplate.queryForList(sql);
    }
}
