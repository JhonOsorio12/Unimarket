package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.CompraDTO;
import co.edu.uniquindio.uniMarket.DTO.CompraGetDTO;
import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.entidades.Compra;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CompraServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compra")
@AllArgsConstructor
public class CompraControlador {

    private final CompraServicio compraServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearCompra(@RequestBody CompraDTO compraDTO) throws Exception{
            compraServicio.crearCompra(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "La compra se creó exitosamente"));
    }

    @GetMapping("/listar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarComprasUsuario(@PathVariable Integer codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                compraServicio.listarComprasUsuario(codigoUsuario)));
    }

    @GetMapping("/obtener/{codigoCompra}")
    public ResponseEntity<MensajeDTO> obtenerCompra(@PathVariable int codigoCompra) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                compraServicio.obtenerCompra(codigoCompra)));
    }

}
