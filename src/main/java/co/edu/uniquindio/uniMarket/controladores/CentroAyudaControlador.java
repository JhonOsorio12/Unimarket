package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.CentroAyudaDTO;
import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CentroAyudaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centroayuda")
@AllArgsConstructor
public class CentroAyudaControlador {

    private final CentroAyudaServicio centroAyudaServicio;

    //Se crea el postmapping
    @PostMapping("/crear")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> crearMensaje(@RequestBody CentroAyudaDTO centroAyudaDTO) throws Exception {
        //Se llama el servicio para crear el mensaje
        centroAyudaServicio.crearMensaje(centroAyudaDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "El mensaje se creó correctamente"));
    }

    //Se crea el getmapping
    @GetMapping("/listar/{codigoCentroAyuda}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarMensajesCentroAyuda(@PathVariable Integer codigoCentroAyuda) throws Exception {
        //Se llama el servicio para listar los mensajes y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                centroAyudaServicio.listarMensajesCentroAyuda(codigoCentroAyuda)));
    }

}
