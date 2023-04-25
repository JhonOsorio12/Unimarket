package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CentroAyudaDTO {

    private String mensaje;

    private LocalDateTime fecha;

    private Integer codigoUsuario;

    private Integer codigoModerador;

}
