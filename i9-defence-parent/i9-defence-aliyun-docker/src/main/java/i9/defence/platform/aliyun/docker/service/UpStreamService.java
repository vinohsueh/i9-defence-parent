package i9.defence.platform.aliyun.docker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpStreamService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUpStream(String messageId, String topic, String payload) {
        String sql = "insert into upstream (messageId, topic, payload, submitDate) value (?, ?, ?, NOW())";
        this.jdbcTemplate.update(sql, new Object[] { messageId, topic, payload });
    }
}
