package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:dataset.sql")
    @DisplayName("Test crear un usuario")
    public void crearUsuarioTest() throws Exception {

        //Se crea el usuario con el servicio de crearUsuario
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito Perez", "pepe12@gmail.com", "1234", "calle 12", "4589");

        int codigo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se espera que si se registra correctamente entonces el servicio no debe retornar 0
        Assertions.assertNotEquals(0, codigo);



    }

    @Test
    @Sql("classpath:dataset.sql")
    @DisplayName("Test eliminar un usuario")
    public void eliminarUsuarioTest() throws Exception {

        try {
            usuarioServicio.eliminarUsuario(1);
            Assertions.assertTrue(true);
        }catch (Exception e){
            throw new ResourceNotFoundException("El usuario no exite");
        }


        /*
        //Para eliminar el usuario primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO("pepe@gmail.com", "Pepito", "1234", "calle 12", "4568");
        int codigo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Una vez creado lo borramos
        int codigoBorrado = usuarioServicio.eliminarUsuario(codigo);

        //Si intentamos buscar un usuario con el código del usuario borrado
        // debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> usuarioServicio.obtenerUsuario(codigoBorrado));
        */


    }

    @Test
    @Sql("classpath:dataset.sql")
    @DisplayName("Test actualizar un usuario")
    public void actualizarUsuarioTest() throws Exception{

        //Para actualizar el usuario primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO("Pepito Perez", "pepe12@gmail.com", "1234", "calle 12", "4589");
        int codigoNuevo = usuarioServicio.registrarUsuario(usuarioDTO);

        //El servicio de actualizar nos retorna el usuario
        UsuarioGetDTO usuarioActualizado = usuarioServicio.actualizarUsuario(codigoNuevo, new UsuarioDTO("pepe1@email.com", "Pepito Perez", "1234", "Calle 123", "1111"));

        //Se comprueba que ahora el teléfono del usuario no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals("2782", usuarioActualizado.getTelefono());

    }

    @Test
    @Sql("classpath:dataset.sql")
    @DisplayName("Test obtener un usuario")
    public void obtenerUsuarioTest() throws Exception{

        try {
            UsuarioGetDTO usuario = usuarioServicio.obtenerUsuario(1);
            Assertions.assertTrue(true);
        }catch (Exception e) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        /*
        //Para obtener el usuario primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO("pepe12@gmail.com", "Pepito Perez", "1234", "calle 12", "4589");
        int codigoNuevo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se llama el servicio para obtener el usuario completo dado su código
        UsuarioGetDTO usuarioGetDTO = usuarioServicio.obtenerUsuario(codigoNuevo);

        //Comprobamos de la dirección que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals("calle 12", usuarioGetDTO.getDireccion());
        */







    }



}
