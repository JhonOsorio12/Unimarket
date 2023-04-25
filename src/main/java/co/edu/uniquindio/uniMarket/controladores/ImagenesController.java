package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CloudinaryServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("api/imagenes")
@AllArgsConstructor
public class ImagenesController {

    private final CloudinaryServicio cloudinaryServicio;

    @PostMapping("/upload")
    public ResponseEntity<MensajeDTO> subirImagen(@RequestParam("file")MultipartFile file) throws Exception{

        File imagen = cloudinaryServicio.convertir(file);
        Map respuesta = cloudinaryServicio.subirImagen(imagen, "Unimarket");
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(
                HttpStatus.OK,
                false,
                respuesta));
    }

    @DeleteMapping("/eliminar_imagen")
    public ResponseEntity<MensajeDTO> eliminarImagen(@RequestBody String id) throws Exception{
        Map respuesta = cloudinaryServicio.eliminarImagen(id);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(
                HttpStatus.OK,
                false,
                respuesta));
    }

}
