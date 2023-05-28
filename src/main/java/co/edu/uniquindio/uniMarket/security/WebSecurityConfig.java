package co.edu.uniquindio.uniMarket.security;


import co.edu.uniquindio.uniMarket.security.config.JwtAuthenticationEntryPoint;
import co.edu.uniquindio.uniMarket.security.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable();
        http.cors();
        //http.authorizeHttpRequests().anyRequest().permitAll();
        http.authorizeHttpRequests()
                .requestMatchers("/api/auth/**", "/api/categorias/**", "/api/listarproductocategoria/**",
                        "/api/listarproductos/**", "/api/listarnombreprecio/**", "/api/listarnombre/**",
                        "/api/listarproductousuario/**", "").permitAll();
        http.authorizeHttpRequests().requestMatchers("api/moderador/**").hasAuthority("MODERADOR");
        http.authorizeHttpRequests().requestMatchers("/api/imagenes/**", "/api/usuario/**"
                ,"/api/producto/**", "/api/comentario/**", "/api/centroayuda/**",
                "/api/compra/**", "/api/crearproductos/**", "/api/obtener/**").hasAuthority("CLIENTE").anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
