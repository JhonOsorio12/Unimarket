package co.edu.uniquindio.uniMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioGetDTO {

    private Integer codigoUsuario;

    private String nombre;

    private String email;

    private String direccion;

    private String telefono;

    private String password;
}
