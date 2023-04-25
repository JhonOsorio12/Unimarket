package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        usuarioServicio.registrarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "El usuario se creó correctamente"));
    }

    @PutMapping("/actualizar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int codigoUsuario, @Valid @RequestBody UsuarioDTO usuarioDTO)throws Exception{
        usuarioServicio.actualizarUsuario(codigoUsuario, usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El usuario se actualizó correctamente"));
    }

    @DeleteMapping("/eliminar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> eliminarUsuario(@PathVariable int codigoUsuario) throws Exception{
        usuarioServicio.eliminarUsuario(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "Usuario eliminado correctamente"));
    }

    @GetMapping("/obtener/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int codigoUsuario) throws Exception{
        usuarioServicio.obtenerUsuario(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "Se obtuvo el usuario correctamente"));
    }
}
