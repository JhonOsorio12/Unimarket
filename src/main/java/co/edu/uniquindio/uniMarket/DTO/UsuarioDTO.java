package co.edu.uniquindio.uniMarket.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    @NotNull
    @Length(max = 100, message = "El correo debe tener máximo 100 caracteres")
    private String email;

    @NotNull
    @Length(min = 3,max = 100, message = "El nombre debe tener máximo 100 caracteres")
    private String nombre;

    @NotNull
    private String password;

    @NotNull
    private String direccion;

    @NotNull
    @Length(max = 12, message = "El password debe tener máximo 12 caracteres")
    private String telefono;





}
