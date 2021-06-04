package com.dubroushchyk.first_spring.security;

import com.dubroushchyk.first_spring.entity.UserAccount;
import com.dubroushchyk.first_spring.enums.EnumRole;
import com.dubroushchyk.first_spring.enums.EnumStatus;
import com.dubroushchyk.first_spring.manager.UserAccountService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements InitializingBean {

    private final UserAccountService userAccountService;

    public SecurityConfig(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void afterPropertiesSet() {
        UserAccount admin = new UserAccount();
        admin.setUserName("nikolay");
        admin.setPassword(passwordEncoder().encode("321"));
        admin.setFirstName("nikolay");
        admin.setLastName("nikolay");
        admin.setRole(EnumRole.ADMIN);
        admin.setStatus(EnumStatus.ACTIVE);
        admin.setCreatedAt(LocalDateTime.now());
        userAccountService.saveUserAccount(admin);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userAccountService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/").permitAll()
                .antMatchers( "/user", "/user/{id}").authenticated()
                .antMatchers("/user/{id}/edit").hasAnyAuthority(EnumRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

}