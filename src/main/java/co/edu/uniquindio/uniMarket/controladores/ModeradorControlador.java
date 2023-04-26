package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ModeradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/moderador")
public class ModeradorControlador {

    @Autowired
    private ModeradorServicio moderadorServicio;

    //Se crea el putmapping
    @PutMapping("/validar")
    public ResponseEntity<MensajeDTO> validarProducto(@RequestBody ProductoModeradorDTO productoModeradorDTO) throws Exception{
        //Se llama el servicio para validar el producto
        moderadorServicio.validarProducto(productoModeradorDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
               "El producto fue APROBADO"));
    }

    //Se crea el putmapping
    @PutMapping("/rechazar")
    public ResponseEntity<MensajeDTO> rechazarProducto(ProductoModeradorDTO productoModeradorDTO) throws Exception {
        //Se llama el servicio para rechazar el producto
        moderadorServicio.rechazarProducto(productoModeradorDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El producto fue RECHAZADO"));
    }

}
