package br.upe.sisepei.sisepei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .antMatchers("/api/auth/**")
            .permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/perfil/**").authenticated()
                .antMatchers(HttpMethod.GET, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                .antMatchers(HttpMethod.GET, "/edital/**").authenticated()
                .antMatchers(HttpMethod.POST, "/edital/**").hasAnyAuthority("COORDENADOR_INOVACAO", "COORDENADOR_PESQUISA", "COORDENADOR_EXTENSAO")
                .antMatchers(HttpMethod.PUT, "/edital/**").hasAnyAuthority("COORDENADOR_INOVACAO", "COORDENADOR_PESQUISA", "COORDENADOR_EXTENSAO")
                .antMatchers(HttpMethod.DELETE, "/edital/**").hasAnyAuthority("COORDENADOR_INOVACAO", "COORDENADOR_PESQUISA", "COORDENADOR_EXTENSAO")
                .antMatchers("/perfis/**").hasAuthority("ADMINISTRADOR")
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();   
    }
    
}
