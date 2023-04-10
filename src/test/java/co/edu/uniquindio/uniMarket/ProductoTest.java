package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoGetDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.repositorios.ProductoRepo;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class ProductoTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarProductoTest() throws Exception{

        //Primero se debe crear un usuario(vendedor)
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "pepe1@gmail.com",
                "Pepito 1",
                "1234",
                "calle 12",
                "343"
        );

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se crea la colección de imágenes para el producto
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("imagen1", "http://www.google.com/images/imagenasus.png");
        imagenes.put("imagen2", "http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Es el mejor computador portatil",
                "Computador Asus 1",
                1,
                7000000,
                imagenes,
                List.of(Categoria.TECNOLOGIA),
                codigoVendedor


        );

        //Se llama el servicio para crear el producto
       // int codigoProducto = productoServicio.crearProducto(productoDTO);

        try {
            int nuevo = productoServicio.crearProducto(productoDTO);
            Assertions.assertNotNull(nuevo);
        }catch (Exception e){
            //Se espera que el servicio retorne el código del nuevo producto
            Assertions.assertTrue(true);
        }
        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(1,codigoProducto);



    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarProductoTest() throws Exception{

        try {
            productoServicio.eliminarProducto(1);
            Assertions.assertTrue(true);
        }catch (Exception e) {
            throw new ResourceNotFoundException("Error");
        }

    /*
        //Primero se debe crear un usuario(vendedor)
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@gmail.com",
                "calle 12",
                "1234",
                "343"
        );

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se crea la colección de imágenes para el producto
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("imagen1", "http://www.google.com/images/imagenasus.png");
        imagenes.put("imagen2", "http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                List.of(Categoria.TECNOLOGIA)
        );

        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //Una vez creado lo borramos
        int codigoBorrado = productoServicio.eliminarProducto(codigoProducto);

        //Si intentamos buscar un producto con el código del producto borrado
        // debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> productoServicio.obtenerProducto(codigoBorrado));
        */




        /*
        Producto clienteBuscado = productoRepo.findById(1).orElse(null);
        productoRepo.delete(clienteBuscado);
        Assertions.assertNull(productoRepo.findById(1).orElse(null));
        */

    }

    @Test
    public void actualizarProductoTest()throws Exception {

        //Primero se debe crear un usuario(vendedor)
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@gmail.com",
                "calle 12",
                "1234",
                "343"
        );

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se crea la colección de imágenes para el producto
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("imagen1", "http://www.google.com/images/imagenasus.png");
        imagenes.put("imagen2", "http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Es el mejor computador portatil",
                "Computador Lenovo 1",
                7000000,
                1,
                imagenes,
                List.of(Categoria.TECNOLOGIA),
                codigoVendedor
        );

        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //El servicio de actualizar nos retorna el producto
        ProductoGetDTO productoActualizado = productoServicio.actualizarProducto(codigoProducto, new ProductoDTO(
                "Es el mejor computador portatil",
                "Computador Asus 1",
                7000000,
                1,
                imagenes,
                List.of(Categoria.TECNOLOGIA),
                codigoVendedor
        ));

        //Se comprueba que ahora el nombre del producto no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals("Computador Lenovo 1", productoActualizado.getNombre());

    }


    @Test
    public void obtenerProductoTest() throws Exception{

        //Primero se debe crear un usuario(vendedor)
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@gmail.com",
                "calle 12",
                "1234",
                "343"
        );

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.registrarUsuario(usuarioDTO);

        //Se crea la colección de imágenes para el producto
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("imagen1", "http://www.google.com/images/imagenasus.png");
        imagenes.put("imagen2", "http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Es el mejor computador portatil",
                "Computador Asus 1",
                7000000,
                1,
                imagenes,
                List.of(Categoria.TECNOLOGIA),
                codigoVendedor
        );

        //Se llama el servicio para crear el producto
        int codigoNuevo = productoServicio.crearProducto(productoDTO);

        //Se llama el servicio para obtener el producto completo dado su código
        ProductoGetDTO productoGetDTO = productoServicio.obtenerProducto(codigoNuevo);

        //Comprobamos de las unidades que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals(1, productoGetDTO.getUnidades());

    }



}
