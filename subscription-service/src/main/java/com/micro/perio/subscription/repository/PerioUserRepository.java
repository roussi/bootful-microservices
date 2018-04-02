package com.micro.perio.subscription.repository;

import com.micro.perio.subscription.domain.PerioUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PerioUserRepository extends JpaRepository<PerioUser, Long> {

    Optional<PerioUser> findOneByLogin(String login);
    Optional<PerioUser> findOneByEmail(String email);

}
