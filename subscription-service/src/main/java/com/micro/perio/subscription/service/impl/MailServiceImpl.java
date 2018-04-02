package com.micro.perio.subscription.service.impl;

import com.micro.perio.subscription.domain.PerioUser;
import com.micro.perio.subscription.service.MailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MailServiceImpl implements MailService {

    @Override
    public void sendActivationMail(PerioUser user) {
        System.out.println("Activation email sent to : "+ user.getEmail());
    }
}
