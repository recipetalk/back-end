package com.solution.recipetalk.security.service;


import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.password.TemporaryPassword;
import com.solution.recipetalk.domain.user.login.password.repository.TemporaryPasswordRepository;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserLoginRepository userLoginRepository;
    private final TemporaryPasswordRepository temporaryPasswordRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserLogin> byUsername = userLoginRepository.findByUsername(username);

        // username 찾지 못하는 예외
        if(byUsername.isEmpty()){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(byUsername.get().getRole().toString()));

        if(temporaryPasswordRepository.findByUserLogin(byUsername.get()).isPresent()) {
            TemporaryPassword byUserLogin = temporaryPasswordRepository.findByUserLogin(byUsername.get())
                    .orElseThrow(UserNotFoundException::new);
            return new UserLoginContext(byUserLogin, roles);
        }

        return new UserLoginContext(byUsername.get(), roles);
    }
}
