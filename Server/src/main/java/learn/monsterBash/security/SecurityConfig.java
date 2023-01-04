package learn.monsterBash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    AuthenticationManager authenticationManager;

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration config) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "api/monsters").permitAll()
                .antMatchers(HttpMethod.GET, "/api/security/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/monsters/viewAll").authenticated()
                .antMatchers(HttpMethod.POST, "/api/monsters/add").hasRole("ADMIN")
                .and()
              //  .addFilter(new JwtRequestFilter(authenticationManager(config), converter))
                .addFilter(new JwtRequestFilter(this.authenticationManager, converter))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }



}
