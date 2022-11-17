package com.sjiwon.studyholic.security.principal;

import com.sjiwon.studyholic.domain.entity.AbstractUserPrincipal;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UserPrincipal implements UserDetails {
    @Serial
    private static final long serialVersionUID = -135425841849865035L;
    private AbstractUserPrincipal userPrincipal;

    public UserPrincipal(AbstractUserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userPrincipal.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPrincipal.getPassword();
    }

    @Override
    public String getUsername() {
        return userPrincipal.getLoginId();
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
