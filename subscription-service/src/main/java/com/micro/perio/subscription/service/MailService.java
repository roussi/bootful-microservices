package com.micro.perio.subscription.service;

import com.micro.perio.subscription.domain.PerioUser;

public interface MailService {

    public void sendActivationMail(PerioUser user);
}
