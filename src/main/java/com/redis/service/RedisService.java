package com.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {
    
    private final RedissonReactiveClient redissonReactiveClient;
    
    public Mono<Void> writeCache(final String bucketName, final byte[] data) {
        return redissonReactiveClient.getBucket(bucketName)
            .set(data)
            .doOnError(error -> log.error("Erro ao tentar escrever dados no chache!", error))
            .onErrorResume(Exception.class, error -> Mono.empty())
            .publishOn(Schedulers.immediate());
    }
    
    public Mono<byte[]> readCache(final String bucketName) {
        return redissonReactiveClient.getBucket(bucketName)
            .get()
            .cast(byte[].class)
            .doOnError(error -> log.error("Erro ao tentar ler dados do cache!", error))
            .onErrorResume(Exception.class, error -> Mono.empty())
            .publishOn(Schedulers.immediate());
    }
    
    public Mono<Boolean> clearCache(final String bucketName) {
        return redissonReactiveClient.getBucket(bucketName)
            .delete()
            .doOnError(error -> log.error("Erro ao tentar deletar dados no cache!", error))
            .onErrorResume(Exception.class, error -> Mono.just(Boolean.FALSE))
            .publishOn(Schedulers.immediate());
    }
}