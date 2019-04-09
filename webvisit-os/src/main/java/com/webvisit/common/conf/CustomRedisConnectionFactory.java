package com.webvisit.common.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/10
 */
public class CustomRedisConnectionFactory {

    private String redisHost = "redis.znzn.me";

    private int redisPort = 6379;

    private int redisTimeout = 10000;

    private String redisAuth = null;

    private int redisDb = 0;

    private int maxActive = 200;

    private int maxWait = -1;

    private int maxIdle = 10;

    private int minIdle = 0;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .usePooling().poolConfig(poolConfig).and().readTimeout(Duration.ofMillis(redisTimeout)).build();

        // 单点redis
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        // 哨兵redis
        // RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
        // 集群redis
        // RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPassword(RedisPassword.of(redisAuth));
        redisConfig.setPort(redisPort);
        redisConfig.setDatabase(redisDb);

        return new JedisConnectionFactory(redisConfig,clientConfig);
    }
}
