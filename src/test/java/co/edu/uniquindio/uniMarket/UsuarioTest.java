package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.CalificacionDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Activo;
import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class UsuarioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Test
    @Sql("classpath:dataset.sql")
    @DisplayName("Test crear un usuario")
    public void crearUsuarioTest() throws Exception {

        //Se crea el usuario con el servicio de crearUsuario
        UsuarioDTO usuarioDTO = new UsuarioDTO("pepe12@gmail.com", "Pepito Perez", "1234", "calle 12", "4589");

        //Se llama el servicio para registrar el usuario
        int codigo = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se espera que si se registra correctamente entonces el servicio no debe retornar 0
        Assertions.assertNotEquals(0, codigo);

    }

    @Test
    @Sql("classpath:dataset.sql")
    @DisplayName("Test eliminar un usuario")
    public void eliminarUsuarioTest() throws Exception {

        //Se llama el servicio para borrar el usuario dado su codigo
        int codigoBorrado = usuarioServicio.eliminarUsuario(1);

        //Se llama el servicio para obtener el usuario completo dado su código
        //para comprobar que ya no existe.
        Assertions.assertThrows(Exception.class, () -> usuarioServicio.obtenerUsuario(codigoBorrado));

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

        //Se llama el servicio para obtener el usuario completo dado su código
        UsuarioGetDTO usuario = usuarioServicio.obtenerUsuario(1);

        //Comprobamos de la dirección que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals("calle 12", usuario.getDireccion());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPasswordTest() throws Exception {

        //Se llama el servicio para poner la nueva contraseña
        Boolean nuevaContraseña = usuarioServicio.cambiarContraseña("jhon@gmail.com", "4321");

        //Esperamos un true como respuesta
        Assertions.assertTrue(nuevaContraseña);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarCalificacionTest() throws Exception {

        //Se crea la calificacion
        CalificacionDTO calificacionDTO = new CalificacionDTO(
                5,
                "Televisor sony",
                1,
                1
        );

        //Se llama el servicio para asignar la calificacion al producto
        int calificacion = usuarioServicio.asignarCalificacion(calificacionDTO);

        //Se comprueba que no sea null
        Assertions.assertNotNull(calificacion);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void promedioProductoTest() throws Exception {

        //Primero se debe crear las imagenes para el producto
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("imagen1", "http://www.google.com/images/imagentenis.png");
        imagenes.put("imagen2", "http://www.google.com/images/imagentenis_original.png");

        //Se crea el producto
        ProductoDTO productoDTO = new ProductoDTO(
                "el mejor televisor",
                "Televisor Sony",
                3000000,
                5,
                imagenes,
                List.of(Categoria.TECNOLOGIA),
                1
        );

        //Se llama el servicio para sacar el promedio de ese producto
        Double promedio = usuarioServicio.promedioProducto(productoDTO);

        //Se comprueba que no se null
        Assertions.assertNotNull(promedio);
    }


}
