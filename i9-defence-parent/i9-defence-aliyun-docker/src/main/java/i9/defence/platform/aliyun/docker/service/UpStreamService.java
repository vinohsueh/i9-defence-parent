package i9.defence.platform.aliyun.docker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import i9.defence.platform.aliyun.docker.service.model.UpStream;

@Service
public class UpStreamService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUpStream(String messageId, String topic, String payload) {
        String sql = "INSERT INTO upstream (messageId, topic, payload, submitDate) VALUE (?, ?, ?, NOW())";
        this.jdbcTemplate.update(sql, new Object[] { messageId, topic, payload });
    }

    public List<UpStream> queryUpStreamList() {
        String sql = "SELECT messageId, topic, payload, submitDate FROM upstream ORDER BY submitDate DESC";
        List<UpStream> list = this.jdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<UpStream>(UpStream.class));
        return list;
    }

    public void deleteUpStream() {
        String sql = "DELETE FROM upstream";
        this.jdbcTemplate.update(sql);
    }
}
