package com.example.semestral.Model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Authentication {
        private String name;
    private List<GrantedAuthority> authorities;
    private Object credentials;
    private Object principal;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    public Object getCredentials() {
        return credentials;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public Object getPrincipal() {
        return principal;
    } 
}
