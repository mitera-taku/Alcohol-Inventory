// package com.example.demo.Config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//         UserDetails user = User.builder()
//                 .username("user")
//                 .password(passwordEncoder.encode("password"))
//                 .roles("USER")
//                 .build();
        
//         return new InMemoryUserDetailsManager(user);
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(authz -> authz
//                 .requestMatchers("/", "/users/log").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .formLogin(form -> form
//                 .loginPage("/users/login")
//                 .defaultSuccessUrl("/main", true)
//                 .permitAll()
//             )
//             .logout(logout -> logout
//                 .permitAll()
//             );
        
//         return http.build();
//     }
// }
