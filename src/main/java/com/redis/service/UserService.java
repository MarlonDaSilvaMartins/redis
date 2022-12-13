package com.redis.service;

import com.redis.model.user.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
//import com.redis.proto.User;
//import com.redis.commons.protobuf.ProtobufUtil;

@Service
@AllArgsConstructor
public class UserService {

    private final RedisService redisService;

    public Mono<UserResponse> readFromCache(Integer userId){
//        return redisService.readCache(userId.toString())
//            .map(data -> ProtobufUtil.parse(User.parser(), data));
        return Mono.empty();
    }
}
/*
* https://developers.google.com/protocol-buffers/docs/javatutorial
* https://redis.io/docs/getting-started/
* https://redis.io/commands/?alpha=s
* */