package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.EmailDTO;
import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailControlador {

    private final EmailServicio emailServicio;

    @PostMapping("/enviar")
    public ResponseEntity<MensajeDTO> enviarEmail(@RequestBody EmailDTO emailDTO) throws Exception{
            emailServicio.enviarEmail(emailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El email fue enviado correctamente"));
    }

}
