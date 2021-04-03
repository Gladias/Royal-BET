package com.gladias.royalbet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    @OneToMany(mappedBy = "user")
    private Set<Bet> bets;

    public User(String login) {
        this.login = login;
    }
}
