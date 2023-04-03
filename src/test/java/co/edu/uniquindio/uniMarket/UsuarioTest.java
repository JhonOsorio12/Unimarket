package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void crearUsuarioTest() throws Exception {

        //Se crea el usuario con el servicio de crearUsuario
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito", "pepe@gmail.com", "calle 12", "1234", "4568");
        int codigo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se espera que si se registra correctamente entonces el servicio no debe retornar 0
        Assertions.assertNotEquals(0, codigo);
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {

        //Para eliminar el usuario primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito", "pepe@gmail.com", "calle 12", "1234", "4568");
        int codigo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Una vez creado lo borramos
        int codigoBorrado = usuarioServicio.eliminarUsuario(codigo);

        //Si intentamos buscar un usuario con el código del usuario borrado
        // debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> usuarioServicio.obtenerUsuario(codigoBorrado));

    }

    @Test
    public void actualizarUsuarioTest() throws Exception{

        //Para actualizar el usuario primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito Perez", "pepe12@gmail.com", "calle 12", "1234", "4589");
        int codigoNuevo = usuarioServicio.registrarUsuario(usuarioDTO);

        //El servicio de actualizar nos retorna el usuario
        UsuarioGetDTO usuarioActualizado = usuarioServicio.actualizarUsuario(codigoNuevo, new UsuarioDTO("Pepito Perez", "pepe1@email.com", "Calle 123", "1234", "1111"));

        //Se comprueba que ahora el teléfono del usuario no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals("2782", usuarioActualizado.getTelefono());


    }

    @Test
    public void obtenerUsuarioTest() throws Exception{
        //Para obtener el usuario primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito Perez", "pepe12@gmail.com", "calle 12", "1234", "4589");
        int codigoNuevo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se llama el servicio para obtener el usuario completo dado su código
        UsuarioGetDTO usuarioGetDTO = usuarioServicio.obtenerUsuario(codigoNuevo);

        //Comprobamos de la dirección que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals("calle 12", usuarioGetDTO.getDireccion());

    }



}
