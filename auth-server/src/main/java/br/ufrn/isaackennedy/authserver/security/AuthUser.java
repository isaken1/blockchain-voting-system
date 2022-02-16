package br.ufrn.isaackennedy.authserver.security;

import br.ufrn.isaackennedy.authserver.domain.enums.Profiles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthUser() {}

    public AuthUser(String cpf, Set<Profiles> perfis) {
        super();
        this.cpf = cpf;
        this.authorities = perfis.stream().map(x ->
                new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return cpf;
    }

    public boolean hasRole(Profiles perfil) {
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

    @Override
    public String getUsername() {
        return cpf;
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
