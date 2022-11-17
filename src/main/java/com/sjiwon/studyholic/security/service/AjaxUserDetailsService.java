package com.sjiwon.studyholic.security.service;

import com.sjiwon.studyholic.domain.entity.AbstractUserPrincipal;
import com.sjiwon.studyholic.domain.entity.user.User;
import com.sjiwon.studyholic.domain.entity.user.repository.UserRepository;
import com.sjiwon.studyholic.exception.StudyholicErrorCode;
import com.sjiwon.studyholic.exception.StudyholicException;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AjaxUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(() -> StudyholicException.type(StudyholicErrorCode.USER_NOT_FOUND));
        return new UserPrincipal(new AbstractUserPrincipal(user));
    }
}
