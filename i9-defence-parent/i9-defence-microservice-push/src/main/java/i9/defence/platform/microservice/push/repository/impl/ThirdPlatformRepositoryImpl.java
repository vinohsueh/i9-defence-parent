package i9.defence.platform.microservice.push.repository.impl;

import i9.defence.platform.microservice.push.repository.ThirdPlatformRepository;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ThirdPlatformRepositoryImpl implements ThirdPlatformRepository {

    @Resource
    private JdbcTemplate jdbcTemplate;
}
