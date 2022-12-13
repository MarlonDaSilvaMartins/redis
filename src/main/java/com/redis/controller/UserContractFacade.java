package com.redis.controller;

import com.redis.model.user.response.UserResponse;
import com.redis.service.UserImplFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserContractFacade {
    
    private final UserImplFacade userImplFacade;
    
    public Mono<UserResponse> readFromCache(Integer userId) {
        return userImplFacade.readFromCache(userId);
    }
}