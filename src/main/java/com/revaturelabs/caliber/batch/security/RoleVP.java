package com.revaturelabs.caliber.batch.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleVP implements GrantedAuthority {
    static private final String role = "Application/ROLE_VP";
    @Override
    public String getAuthority() {
        return role;
    }
}
