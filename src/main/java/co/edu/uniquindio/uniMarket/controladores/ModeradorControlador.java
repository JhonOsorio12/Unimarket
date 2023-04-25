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
@RequestMapping("api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class ModeradorControlador {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @PutMapping("/validar")
    public ResponseEntity<MensajeDTO> validarProducto(@RequestBody ProductoModeradorDTO productoModeradorDTO) throws Exception{
        moderadorServicio.validarProducto(productoModeradorDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
               "El producto fue APROBADO"));
    }

    @PutMapping("/rechazar")
    public ResponseEntity<MensajeDTO> rechazarProducto(ProductoModeradorDTO productoModeradorDTO) throws Exception {
            moderadorServicio.rechazarProducto(productoModeradorDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El producto fue RECHAZADO"));
    }

}
