package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.ComentarioDTO;
import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentario")
@AllArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    //Se crea el postmapping
    @PostMapping("/crearcomentario")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> crearComentario(@RequestBody ComentarioDTO comentarioDTO) throws Exception{
        //Se llama el servicio para crear el comentario y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                comentarioServicio.crearComentario(comentarioDTO)));
    }

    //Se crea el getmapping
    @GetMapping("/listarcomentario/{codigoProducto}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarComentarios(@PathVariable Integer codigoProducto) throws Exception{
        //Se llama el servicio para listar los comentarios y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                comentarioServicio.listarComentarios(codigoProducto)));
    }

}
