package com.irm.security;

import com.irm.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Or customize based on user status
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Or customize based on user status
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Or customize
    }

    @Override
    public boolean isEnabled() {
        return true; // Or customize based on user.isActive()
    }

    public User getUser() {
        return this.user;
    }
}
