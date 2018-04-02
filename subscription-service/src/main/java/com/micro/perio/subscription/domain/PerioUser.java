package com.micro.perio.subscription.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PerioUser {

    @Id
    @GeneratedValue
    Long id;

    String firstname;
    String lastname;

    @NotNull
    @UniqueElements
    String email;

    @NotNull
    @UniqueElements
    @Size(min = 4, max = 20)
    String login;

    @NotNull
    @JsonIgnore
    @Size(min = 8)
    String password;

    Integer age;
    Boolean isBlocked;
    Boolean isActivated;
    Boolean isExpired;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "perio_user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "PerioUser{" +
                "login='" + login + '\'' +
                '}';
    }
}
