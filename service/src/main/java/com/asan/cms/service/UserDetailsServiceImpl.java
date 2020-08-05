package com.asan.cms.service;

import com.asan.cms.repository.UserRepository;
import com.asan.cms.to.User;
import com.asan.cms.type.Principal;
import com.asan.cms.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Get user {} from database", username);
        User user = repository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        LOGGER.info(JsonUtil.jsonObject(user));
        Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> {
                    Assert.isTrue(!role.getName().startsWith("ROLE_"), () -> role + " cannot start with ROLE_ (it is automatically added)");
                    return new SimpleGrantedAuthority("ROLE_" + role.getName());
                }).collect(Collectors.toSet());
        LOGGER.info(JsonUtil.jsonObject(grantedAuthorities));
        Principal principal = new Principal(user.getUsername(), user.getPassword(), grantedAuthorities);
        principal.setEmail(user.getEmail());
        return principal;
    }
}
