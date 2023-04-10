package co.edu.uniquindio.uniMarket.security.jwt;

import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        MensajeDTO dto = new MensajeDTO(HttpStatus.UNAUTHORIZED, true, "Token no encontrado o inválido");

        response.setContentType("application/jason");
        response.setStatus(dto.getEstado().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();

    }
}
