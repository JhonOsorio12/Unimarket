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
                        "/api/listarproductousuario/**", "/api/listarproductoscategoria/**",
                        "/api/listarcategorias/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/moderador/**", "/api/listarestado/**",
                "/api/actualizarestado/**", "/api/refresh/**").hasAuthority("MODERADOR");
        http.authorizeHttpRequests().requestMatchers("/api/imagenes/**", "/api/usuario/**"
                ,"/api/producto/**", "/api/comentario/**", "/api/centroayuda/**",
                "/api/compra/**", "/api/crearproducto/**", "/api/obtener/**",
                "/api/crearcompra/**", "/api/listarcompra/**", "/api/crearcompra/**",
                "/api/obtenercompra/**", "/api/actualizarproducto/**", "/api/eliminarproducto/**",
                "/api/obtenerproducto/**", "/api/listarproductosfavoritos/**", "/api/crearfavorito/**",
                "/api/eliminarfavorito/**", "/api/actualizarunidades/**", "/api/imagenes/**", "/api/upload/**",
                "/api/eliminar_imagen/**", "/api/crearcomentario/**", "/api/listarcomentario/**",
                "/api/crearcentroayuda/**", "/api/listarcentroayuda/**", "/api/actualizarusuario/**",
                "/api/eliminarusuario/**", "/api/obtenerusuario/**", "/api/asignarcalificacion/**",
                "/api/promedioproducto/**", "/api/refresh/**").hasAuthority("CLIENTE").anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
