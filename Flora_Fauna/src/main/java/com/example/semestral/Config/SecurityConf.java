package com.example.semestral.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.semestral.Auth.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
class SecurityConf {

        @Autowired
        JWTAuthorizationFilter jwtAuthorizationFilter;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {

                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(request -> request
                                                .requestMatchers(HttpMethod.POST,"/auth/**", "/api/post").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/perfil/**").authenticated()
                                                .requestMatchers("/img/**", "/css/**", "/js/**", "/Registro.html", "/Login.html", "/PasswordReset.html", "/Ayuda.html").permitAll()
                                                .requestMatchers("/login", "/register", "/reset_password", "/help").permitAll()
                                                .requestMatchers("/RegistrarFloraFauna.html", "/post").hasAuthority("Usuario")
                                                .requestMatchers("/change_password", "/PasswordResetConfirm.html").permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

}
