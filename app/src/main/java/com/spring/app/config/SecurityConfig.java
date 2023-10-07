package com.spring.app.config;


import com.spring.common.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    MyUserDetailsService MyuserDetailsService;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authManager(MyUserDetailsService myUserDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(getPasswordEncoder());
        authProvider.setUserDetailsService(myUserDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        http

                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.requestMatchers("/testRoutes/**").permitAll();
                        auth.requestMatchers("/admin**").hasAuthority("ADMIN");
                        auth.requestMatchers("/tutee/**").hasAuthority("TUTEE").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
                        auth.requestMatchers("/tutor/**").hasAuthority("TUTOR").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    auth.anyRequest().authenticated();
                })
                .formLogin().successHandler(authenticationSuccessHandler()).and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }


}
