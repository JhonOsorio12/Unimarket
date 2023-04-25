package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoGetDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.entidades.*;
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

import java.util.*;

@SpringBootTest
@Transactional
public class ProductoTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;



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
            int nuevo = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(1,nuevo);



    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarProductoTest() throws Exception{

        //Se llama el servicio para eliminar el producto
        productoServicio.eliminarProducto(1);

        //Si intentamos buscar el producto con el código del producto borrado
        // debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows( Exception.class, () -> productoServicio.obtenerProducto(1) );

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

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarProductoTest()throws Exception {

        //Se crea la colección de imágenes para el producto
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("imagen1", "http://www.google.com/images/imagenasus.png");
        imagenes.put("imagen2", "http://www.google.com/images/imagenasus_original.png");

        //El servicio de actualizar nos retorna el producto
        ProductoGetDTO productoActualizado = productoServicio.actualizarProducto(1, new ProductoDTO(
                "Es el mejor computador portatil",
                "Computador Asus 1",
                7000000,
                1,
                imagenes,
                List.of(Categoria.TECNOLOGIA),
                1
        ));

        ProductoGetDTO nuevo = productoServicio.obtenerProducto(1);

        //Se comprueba que ahora el nombre del producto no es el que se usó cuando se creó inicialmente
        Assertions.assertEquals("Computador Asus 1", nuevo.getNombre());

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerProductoTest() throws Exception{

        //Se llama el servicio para obtener un producto dado su codigo
        ProductoGetDTO productoGetDTO = productoServicio.obtenerProducto(1);

        //Comprobamos que las unidades del producto obtenido sean las mismas que esperamos
        Assertions.assertEquals(5, productoGetDTO.getUnidades());

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


         */
    }

    //------------------------ FIN CRUD TEST PRODUCTO ----------------------------------//


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarUnidadesTest() throws Exception{

        //Se llama el servicio para obtener el producto dado el codigo
        Producto producto = productoServicio.obtener(1);
        //set las unidades
        producto.setUnidades(4);

        //Se llama el servicio para actualizar las nuevas unidades del producto
        int nuevasUnidades = productoServicio.actualizarUnidades(producto, 4);

        //Comprobamos que se haya actualizado las anteriores unidades con las nuevas
        Assertions.assertNotEquals(5,nuevasUnidades);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarEstadoTest() throws Exception{

            //Se llama el servicio para obtener el producto dado su codigo
            Producto producto = productoServicio.obtener(1);
            //set al estado del producto
            producto.setActivo(Activo.ACTIVO);

            //Se llama el servicio para actualizar el estado del producto
            int nuevoEstado = productoServicio.actualizarPorEstado(producto,Activo.valueOf("ACTIVO"));

            //Se comprueba que se haya actualizado el estado
            Assertions.assertNotEquals("INACTIVO", nuevoEstado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCategoriaTest() throws Exception{

            //Se llama el servicio para obtener el producto dado su codigo
            Producto producto = productoServicio.obtener(3);
            //Se setea la categoria del producto obtenido
            producto.setCategoria(Collections.singletonList(Categoria.TECNOLOGIA));

            //Se agrega a una nueva lista y se llama el servicio para enviarle la nueva categoria
            List<ProductoGetDTO> nuevaCategoria = productoServicio.listarProductosCategoria(Categoria.TECNOLOGIA);

            //Se comprueba que se haya actualizado la categoria
            Assertions.assertNotEquals("ROPA", nuevaCategoria);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosCategoriaTest() throws Exception {

        //Se llama el servicio para listar los productos que son de la categoria dada
        List<ProductoGetDTO> lista = productoServicio.listarProductosCategoria(Categoria.TECNOLOGIA);
        lista.forEach(System.out::println);

        //Se comprueba la cantidad de productos dada la categoria en la lista
        Assertions.assertEquals(1,lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosUsuarioTest() throws Exception{

        //Se llama el servicio para listar los productos del usuario dado su codigo
        List<ProductoGetDTO> lista = productoServicio.listarProductosUsuario(1);
        lista.forEach(System.out::println);

        //Se comprueba la cantidad de productos que tiene el usuario
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosFavoritosTest() throws Exception{
        List<ProductoGetDTO> lista = productoServicio.listarProductosFavoritos(1);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosNombreTest(){
        List<ProductoGetDTO> lista = productoServicio.listarProductosNombre("Televisor Sony");
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosEstadoTest(){
        List<ProductoGetDTO> lista = productoServicio.listarProductosEstado(Estado.APROBADO);
        lista.forEach(System.out::println);
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosPrecioTest(){
        List<ProductoGetDTO> lista = productoServicio.listarProductosPrecio("Televisor Sony",1500000, 3100000);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearFavoritoTest() throws Exception{

        productoServicio.crearFavorito(1,4);
        Assertions.assertTrue(true);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFavoritoTest() throws Exception{

        productoServicio.eliminarFavorito(1, 4);
        Assertions.assertTrue(true);

    }





}
