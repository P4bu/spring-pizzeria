package com.pizza.spring_pizzeria.service;

import com.pizza.spring_pizzeria.persistence.entity.UserEntity;
import com.pizza.spring_pizzeria.persistence.entity.UserRoleEntity;
import com.pizza.spring_pizzeria.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " Not Found"));

        System.out.println(userEntity);

        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorityList(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisable())
                .build();
    }

    private String[] getAuthorities(String role) {
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"random_order"};
        }

        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorityList(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role));

            for(String authority: this.getAuthorities(role)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}
