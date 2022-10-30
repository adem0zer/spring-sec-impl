package com.example.ogad.services.impl;

import com.example.ogad.model.User;
import com.example.ogad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if (Objects.nonNull(user)) {
            String bcryptHashString = at.favre.lib.crypto.bcrypt.BCrypt.withDefaults().hashToString(10, user.getPassword().toCharArray());
            return new org.springframework.security.core.userdetails.User(user.getUserName(), bcryptHashString,
                    getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getUserRoles().forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority("" + userRole.getRoles().get(0).getRoleName()));
        });
        return authorities;
    }
}
