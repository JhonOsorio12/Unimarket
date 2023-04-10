package co.edu.uniquindio.uniMarket.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SesionDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

   // private int tipo;

}
