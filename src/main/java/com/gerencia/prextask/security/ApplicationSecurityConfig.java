package com.gerencia.prextask.security;

import static com.gerencia.prextask.security.ApplicationUserRole.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
            // .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(CLIENTE_WRITE.name())
            // .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(CLIENTE_WRITE.name())
            // .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(CLIENTE_WRITE.name())
            // .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(ADMIN.name(), USER.name())
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/clientes", true)
                .passwordParameter("password")
                .usernameParameter("username")
            .and()
            .rememberMe() //default 2 weeks
                .rememberMeParameter("remember-me")
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails joaquinUser = User.builder()
                .username("joaquin")
                .password(passwordEncoder.encode("clave"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails karenUser = User.builder()
                .username("karen")
                .password(passwordEncoder.encode("1234"))
                .authorities(USER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
            joaquinUser, karenUser
        );
    }
    
}