package com.gerencia.prextask.auth;

import java.util.List;
import java.util.Optional;

import com.gerencia.prextask.security.ApplicationUserRole;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(e -> e.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                "joaquin", 
                passwordEncoder.encode("clave"), 
                ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true            
            ),
            new ApplicationUser(
                "karen", 
                passwordEncoder.encode("1234"), 
                ApplicationUserRole.USER.getGrantedAuthorities(),
                true,
                true,
                true,
                true            
            )
        );

        return applicationUsers;
    }

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}