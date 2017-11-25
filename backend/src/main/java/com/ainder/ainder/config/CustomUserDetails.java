package com.ainder.ainder.config;

import com.ainder.ainder.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Collection<GrantedAuthority> authorities;
    private String password;
    private String username;

    public CustomUserDetails(User byUsername) {
        this.username = byUsername.getName();
        this.password = byUsername.getPassword();
        GrantedAuthority auth = new SimpleGrantedAuthority(byUsername.getRole().getName());
        authorities = new ArrayList<>();
        authorities.add(auth);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
