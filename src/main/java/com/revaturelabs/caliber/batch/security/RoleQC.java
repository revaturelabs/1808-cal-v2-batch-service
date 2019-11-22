package com.revaturelabs.caliber.batch.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleQC implements GrantedAuthority {
    private static final String role = "Application/ROLE_QC";

    @Override
    public String getAuthority() {
        return role;
    }
}
