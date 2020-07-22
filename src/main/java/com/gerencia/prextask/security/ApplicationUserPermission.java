package com.gerencia.prextask.security;

public enum ApplicationUserPermission {
    CLIENTE_READ("cliente:read"),
    CLIENTE_WRITE("cliente:write");

    private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

    public String getPermission() {
        return permission;
    }

    
    
}