package com.redis.service;

import com.redis.model.user.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserImplFacade {
    
    private final UserService userService;
    
    public Mono<UserResponse> readFromCache(Integer userId) {
        return userService.readFromCache(userId);
    }
}