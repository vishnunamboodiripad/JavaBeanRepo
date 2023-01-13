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
                .antMatchers(HttpMethod.POST, "/api/security/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/monsters/viewAll").permitAll()
                .antMatchers(HttpMethod.POST, "/api/add/battle").permitAll()
                .antMatchers(HttpMethod.GET, "/api/monster/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/equipment/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/element/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/affinity/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/weather/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/location/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/findRecord/*").authenticated()
//                .antMatchers(HttpMethod.GET, "/api/manage").hasRole("admin")
//                .antMatchers(HttpMethod.POST, "/api/add/**").hasRole("admin")
//                .antMatchers(HttpMethod.PUT, "/api/edit/**").hasRole("admin")
//                .antMatchers(HttpMethod.DELETE, "/api/delete/**").hasRole("admin")
                //.antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(this.authenticationManager, converter))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }



}
