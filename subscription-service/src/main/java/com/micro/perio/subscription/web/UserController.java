package com.micro.perio.subscription.web;

import com.micro.perio.subscription.domain.PerioUser;
import com.micro.perio.subscription.repository.PerioUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    PerioUserRepository perioUserRepository;
    Environment environment;

    public UserController(PerioUserRepository perioUserRepository, Environment environment) {
        this.perioUserRepository = perioUserRepository;
        this.environment=environment;
    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<PerioUser>> getAllUsers(){
        logger.info("Get all users");
        System.out.println("--------- getAllUsers -------");
        return ResponseEntity.status(HttpStatus.OK).body(perioUserRepository.findAll());
    }

    @GetMapping(value = "/message")
    public String sayHello(){
        return environment.getProperty("message");
    }
}
