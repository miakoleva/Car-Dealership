package com.example.cardealershipapp.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_RENTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
