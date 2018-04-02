package com.micro.perio.subscription.web;


import com.micro.perio.subscription.domain.PerioUser;
import com.micro.perio.subscription.repository.PerioUserRepository;
import com.micro.perio.subscription.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    PerioUserRepository perioUserRepository;

    MailService mailService;

    public AccountController(PerioUserRepository perioUserRepository, MailService mailService) {
        this.perioUserRepository = perioUserRepository;
        this.mailService= mailService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody PerioUser user){

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        logger.info("subscribe new user "+ user.toString());
        Assert.notNull(user, "#ERROR : User can't be null");

        return perioUserRepository.findOneByLogin(user.getLogin().toLowerCase())
                .map(perioUser-> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                .orElseGet(
                        ()-> perioUserRepository.findOneByEmail(user.getEmail().toLowerCase())
                        .map(perioUser -> new ResponseEntity<>("email already exist", textPlainHeaders, HttpStatus.BAD_REQUEST))
                        .orElseGet(
                                ()-> {
                                    PerioUser createdUser= perioUserRepository.save(user);
                                    mailService.sendActivationMail(createdUser);
                                    return new ResponseEntity<>(HttpStatus.CREATED);
                                }
                        )
                );
        }
}
