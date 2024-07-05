package com.pizza.spring_pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Personalizacion de la configuración de seguridad
                .authorizeHttpRequests(customRequest -> {
                    customRequest
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/customers/**").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN","CUSTOMER")
                            .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                            .requestMatchers("/api/orders/random").hasAuthority("random_order")
                            .requestMatchers("/api/orders/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                //.httpBasic(Customizer.withDefaults());
                //desactivando la protección CSRF (Cross-Site Request Forgery)
                // es una característica de seguridad que ayuda a prevenir ciertos tipos de ataques, pero en algunos casos, puedes querer desactivarla
                //habilitando la configuración de CORS (Cross-Origin Resource Sharing)
                //habilitando la autenticación básica HTTP
        // construyendo la configuración de seguridad y devolviéndola
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService memoryUsers(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    } */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
