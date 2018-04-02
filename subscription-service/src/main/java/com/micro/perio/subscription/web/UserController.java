package com.micro.perio.subscription.web;

import com.micro.perio.subscription.domain.PerioUser;
import com.micro.perio.subscription.repository.PerioUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RefreshScope
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${message: 'message not founded'}")
    String message;

    PerioUserRepository perioUserRepository;

    public UserController(PerioUserRepository perioUserRepository) {
        this.perioUserRepository = perioUserRepository;
    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<PerioUser>> getAllUsers(){
        logger.info("Get all users");
        System.out.println("--------- getAllUsers -------");
        return ResponseEntity.status(HttpStatus.OK).body(perioUserRepository.findAll());
    }

    @GetMapping(value = "/message")
    public String sayHello(){
        return message;
    }
}
