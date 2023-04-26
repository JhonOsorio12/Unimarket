package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.CalificacionDTO;
import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    //Se crea el postmapping
    @PostMapping("/crear")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        //Se llama el servicio para registrar el usuario
        usuarioServicio.registrarUsuario(usuarioDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "El usuario se creó correctamente"));
    }

    //Se crea el putmapping
    @PutMapping("/actualizar/{codigoUsuario}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int codigoUsuario, @Valid @RequestBody UsuarioDTO usuarioDTO)throws Exception{
        //Se llama el servicio para actualizar el usuario
        usuarioServicio.actualizarUsuario(codigoUsuario, usuarioDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El usuario se actualizó correctamente"));
    }

    //Se crea el deletemapping
    @DeleteMapping("/eliminar/{codigoUsuario}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> eliminarUsuario(@PathVariable int codigoUsuario) throws Exception{
        //Se llama el servicio para eliminar el usuario
        usuarioServicio.eliminarUsuario(codigoUsuario);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "Usuario eliminado correctamente"));
    }

    //Se crea el getmapping
    @GetMapping("/obtener/{codigoUsuario}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int codigoUsuario) throws Exception{
        //Se llama el servicio para obtener el usuario
        usuarioServicio.obtenerUsuario(codigoUsuario);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "Se obtuvo el usuario correctamente"));
    }

    //Se crea el getmapping
    @GetMapping("/asignarcalificacion")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> asignarCalificacion(@Valid @RequestBody CalificacionDTO calificacionDTO) throws Exception{
        //Se llama el servicio para asignar la calificacion del usuario al producto
        usuarioServicio.asignarCalificacion(calificacionDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "Se asignó la clasificación correctamente"));
    }

    //Se crea el getmapping
    @GetMapping("/promedioproducto")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> promedioProducto(@Valid @RequestBody ProductoDTO productoDTO) throws Exception{
        //Se llama el servicio para traer el promedio de la calificacion de un producto
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                usuarioServicio.promedioProducto(productoDTO)));
    }
}
