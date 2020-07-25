package com.gerencia.prextask.security;

import static com.gerencia.prextask.security.ApplicationUserPermission.*;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(CLIENTE_READ, CLIENTE_WRITE)), 
    USER(Sets.newHashSet(CLIENTE_READ));

    private final Set<ApplicationUserPermission> permissions;

    private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

}