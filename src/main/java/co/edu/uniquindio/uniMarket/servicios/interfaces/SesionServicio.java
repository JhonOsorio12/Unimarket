package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.SesionDTO;
import co.edu.uniquindio.uniMarket.DTO.TokenDTO;
import lombok.Getter;
import org.springframework.stereotype.Service;

public interface SesionServicio {

    TokenDTO login(SesionDTO sesionDTO);

    TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception;

    void logout(int codigoUsuario);

}
