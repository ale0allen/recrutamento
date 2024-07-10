package br.com.recrutamento.payload.response;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtResponse implements UserDetails {
    private String jwt;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    // Construtor com apenas o token JWT
    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    // Construtor conforme solicitado
    public JwtResponse(String jwt, String username, Collection<? extends GrantedAuthority> authorities) {
        this.jwt = jwt;
        this.username = username;
        this.authorities = authorities;
    }

    // Implementação dos métodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // Implementar conforme necessário
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implementar conforme necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implementar conforme necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implementar conforme necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Implementar conforme necessário
    }

    // Getters e Setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
