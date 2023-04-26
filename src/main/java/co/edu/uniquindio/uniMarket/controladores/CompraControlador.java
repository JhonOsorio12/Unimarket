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

    //Se crea el postmapping
    @PostMapping("/crear")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> crearCompra(@RequestBody CompraDTO compraDTO) throws Exception{
        //Se llama el servicio para crear la compra
        compraServicio.crearCompra(compraDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "La compra se creó exitosamente"));
    }

    //Se crea el getmapping
    @GetMapping("/listar/{codigoUsuario}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarComprasUsuario(@PathVariable Integer codigoUsuario) throws Exception{
        //Se llama el servicio para listar la compra y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                compraServicio.listarComprasUsuario(codigoUsuario)));
    }

    //Se crea el getmapping
    @GetMapping("/obtener/{codigoCompra}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> obtenerCompra(@PathVariable int codigoCompra) throws Exception{
        //Se llama el servicio para obtener la compra y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                compraServicio.obtenerCompra(codigoCompra)));
    }

}
