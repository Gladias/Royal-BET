package com.gladias.royalbet.security;

import com.gladias.royalbet.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long id;

    private String login;
    private String password;
    private String email;

    // TODO: authorities
    //private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal of(@NonNull UserEntity userEntity) {
        return new UserPrincipal(userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getEmail());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
