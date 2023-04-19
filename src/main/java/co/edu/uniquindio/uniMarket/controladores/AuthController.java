package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.*;
import co.edu.uniquindio.uniMarket.servicios.interfaces.SesionServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioServicio usuarioServicio;
    private final SesionServicio sesionServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser){
        TokenDTO jwtTokenDTO = sesionServicio.login(loginUser);
        return ResponseEntity.status(HttpStatus.OK).body( new
                MensajeDTO(HttpStatus.OK, false, jwtTokenDTO));
    }

    @PostMapping("/crear_cliente")
    public ResponseEntity<MensajeDTO> registrarCliente(@Valid @RequestBody UsuarioDTO usuario) throws Exception{
        usuarioServicio.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body( new
                MensajeDTO(HttpStatus.CREATED, false, "Cliente creado correctamente"));
    }


}
