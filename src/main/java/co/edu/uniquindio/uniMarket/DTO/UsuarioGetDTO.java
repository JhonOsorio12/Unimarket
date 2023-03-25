package co.edu.uniquindio.uniMarket.DTO;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioGetDTO {

    private Integer codigoUsuario;

    private String nombre;

    private String email;

    private String direccion;

    private String telefono;

}
