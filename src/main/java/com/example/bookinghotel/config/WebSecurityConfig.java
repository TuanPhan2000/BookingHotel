package com.example.bookinghotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DaoAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/about/**","/blog/**", "/blogSingle/**","/contact/**", "/home", "/register/**","/restaurant/**", "/rooms/**", "/roomsSingle/**", "/css/**","/fonts/**", "/images/**", "/js/**", "/scss/**", "/login/**", "/registration/**", "/registrationConfirm/**").permitAll()
                .antMatchers("/admin/productsPage").hasAnyAuthority("ADMIN","CREATOR","EDITOR")
                .antMatchers("/admin/productFormPage").hasAnyAuthority("ADMIN","CREATOR")
                .antMatchers("/admin/addProduct").hasAnyAuthority("ADMIN", "CREATOR")
                .antMatchers("/admin/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/delete/**").hasAuthority("ADMIN")
                .antMatchers("/admin/usersPage/**").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/cartPage/**").hasAuthority("USER")
                .antMatchers("/checkoutPage/**").hasAuthority("USER")
                .antMatchers("/billPage/**").hasAuthority("USER")
                .antMatchers("/admin/updateProductPage/**").hasAuthority("EDITOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }
}
