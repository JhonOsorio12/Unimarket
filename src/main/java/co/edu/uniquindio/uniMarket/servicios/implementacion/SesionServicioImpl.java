package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.SesionDTO;
import co.edu.uniquindio.uniMarket.DTO.TokenDTO;
import co.edu.uniquindio.uniMarket.security.jwt.JwtProvider;
import co.edu.uniquindio.uniMarket.servicios.interfaces.SesionServicio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SesionServicioImpl implements SesionServicio {

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    public SesionServicioImpl() {
        this.jwtProvider = null;
        this.authenticationManager = null;
    }

    @Override
    public TokenDTO login(SesionDTO dto) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new TokenDTO(token);
    }

    @Override
    public void logout(int codigoUsuario) {

    }
}
