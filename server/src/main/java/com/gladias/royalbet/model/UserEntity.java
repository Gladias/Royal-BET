package com.gladias.royalbet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String email;

    private double money;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BetEntity> bets;

    public UserEntity(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
