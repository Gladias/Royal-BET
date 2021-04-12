package com.gladias.royalbet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    @OneToMany(mappedBy = "user")
    private Set<BetEntity> bets;
}
