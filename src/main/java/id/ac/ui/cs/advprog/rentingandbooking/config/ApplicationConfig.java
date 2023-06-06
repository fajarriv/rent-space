package id.ac.ui.cs.advprog.rentingandbooking.config;

import id.ac.ui.cs.advprog.rentingandbooking.dto.auth.UserResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            ResponseEntity<UserResponse> response = restTemplate.getForEntity(String.format("%s/user/get-by-email/%s", authServiceUrl, username), UserResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                UserResponse userResponse = response.getBody();
                return User
                        .builder()
                        .firstname(userResponse.getFirstname())
                        .lastname(userResponse.getLastname())
                        .email(userResponse.getEmail())
                        .permissions(userResponse.getPermissions())
                        .password(userResponse.getPassword())
                        .active(userResponse.isActive())
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
