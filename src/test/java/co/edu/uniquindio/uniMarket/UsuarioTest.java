package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void crearUsuarioTest(){

     try {
         UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito", "pepe@gmail.com", "calle 12", "1234", "4568");
         usuarioServicio.registrarUsuario(usuarioDTO);
     }
     catch (Exception e){
         e.printStackTrace();
     }
    }

    @Test
    public void eliminarUsuarioTest(){
        try {
            usuarioServicio.eliminarUsuario(2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void actualizarUsuarioTest(){
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito Perez", "pepe@gmail.com", "calle 12", "1234", "4568");
            usuarioServicio.actualizarUsuario(2,usuarioDTO);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void obtenerUsuarioTest(){}

    public void listarUsuarioTest(){}

}
