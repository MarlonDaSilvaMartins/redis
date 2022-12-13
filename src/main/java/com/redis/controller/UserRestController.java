package com.redis.controller;

import com.redis.model.user.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/redis")
public class UserRestController {
    
    private final UserContractFacade userContractFacade;
    
    @GetMapping("/{userId}")
    public Mono<UserResponse> readFromCache(
        @PathVariable
        Integer userId) {
        
        return userContractFacade.readFromCache(userId);
    }
}