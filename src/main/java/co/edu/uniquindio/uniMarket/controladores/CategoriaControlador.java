package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CategoriaSevicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
public class CategoriaControlador {

    private final CategoriaSevicio categoriaSevicio;

    @GetMapping("/listarcategorias")
    public ResponseEntity<MensajeDTO> listarCategorias() throws Exception {
        //Se llama el servicio para listar las categorias y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                categoriaSevicio.listarCategoria()));


    }

}
