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

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearMensaje(@RequestBody CentroAyudaDTO centroAyudaDTO) throws Exception {
            centroAyudaServicio.crearMensaje(centroAyudaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "El mensaje se creó correctamente"));
    }

    @GetMapping("/listar/{codigoCentroAyuda}")
    public ResponseEntity<MensajeDTO> listarMensajesCentroAyuda(@PathVariable Integer codigoCentroAyuda) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                centroAyudaServicio.listarMensajesCentroAyuda(codigoCentroAyuda)));
    }

}
