package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CentroAyudaGetDTO {

    private Integer codigo;

    private String mensaje;

    private LocalDate fecha;

    private Integer codigoProducto;

    private Integer codigoUsuario;

}
