package com.redis.commons.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.ByteArrayCodec;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisServerConfig {
    
    private static final String SENTINELS_SEPARATOR = ";";
    
    private final String redisSentinelHosts;
    private final String redisSentinelsMaster;
    private final short threads;
    private final short timeout;
    private final byte retryAttempts;
    
    public RedisServerConfig(
        @Value("${redis.sentinels.hosts}") final String redisSentinelHosts,
        @Value("${redis.sentinels.master_name}") final String redisSentinelMaster,
        @Value("${redis.threads}") final short threads,
        @Value("${redis.timeout_ms:300}") final short timeout,
        @Value("${redis.retry_attempts:0}") final byte retryAttempts) {
        this.redisSentinelHosts = redisSentinelHosts;
        this.redisSentinelsMaster = redisSentinelMaster;
        this.threads = threads;
        this.timeout = timeout;
        this.retryAttempts = retryAttempts;
    }
    
    @Bean(destroyMethod = "shutdown")
    public RedissonReactiveClient redissonReactiveClient() {
        final var config = new Config();
        config.setCodec(new ByteArrayCodec());
        config.setTransportMode(TransportMode.EPOLL);
        config.useSentinelServers()
            .addSentinelAddress(redisSentinelHosts.split(SENTINELS_SEPARATOR))
            .setMasterName(redisSentinelsMaster)
            .setTimeout(timeout)
            .setClientName("redis-test")
            .setKeepAlive(true)
            .setRetryAttempts(retryAttempts);
        config.setNettyThreads(threads);
        return Redisson.create(config).reactive();
    }
}