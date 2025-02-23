package nechto.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/*/**").hasRole("OWNER")
                        .antMatchers("/scores/**", "/game/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT,"/user/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/user/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET).permitAll()
                        .anyRequest()
                        .authenticated())
                        .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}

