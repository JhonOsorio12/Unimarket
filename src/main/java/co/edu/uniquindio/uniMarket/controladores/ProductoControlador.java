package co.edu.uniquindio.uniMarket.controladores;

import co.edu.uniquindio.uniMarket.DTO.MensajeDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.entidades.Activo;
import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@AllArgsConstructor
public class ProductoControlador {

    private ProductoServicio productoServicio;

    //Se crea el postmapping
    @PostMapping("/crearproducto")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) throws Exception{
        //Se llama el servicio para crear el producto
        productoServicio.crearProducto(productoDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "El producto se creó exitosamente"));
    }

    //Se crea el putmapping
    @PutMapping("/actualizarproducto/{codigoProducto}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> actualizarProducto(@PathVariable Integer codigoProducto, @Valid @RequestBody ProductoDTO productoDTO) throws Exception{
        productoServicio.actualizarProducto(codigoProducto, productoDTO);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El producto se actualizó exitosamente"));
    }

    //Se crea el deletemapping
    @DeleteMapping("/eliminarproducto/{codigoProducto}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> eliminarProducto(@PathVariable int codigoProducto) throws Exception{
        productoServicio.eliminarProducto(codigoProducto);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El producto se actualizó exitosamente"));
    }

    //Se crea el getmapping
    @GetMapping("/obtenerproducto/{codigoProducto}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable int codigoProducto) throws Exception{

        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.obtenerProducto(codigoProducto)));
    }

    //Se crea el putmapping
    @PutMapping("/actualizarestado/{activo}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> actualizarPorEstado(@Valid @RequestBody Producto producto,@PathVariable Activo activo) throws Exception{
        //Se llama el servicio para actualizar el estado del producto
        productoServicio.actualizarPorEstado(producto, activo);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El estado se actualizó exitosamente"));
    }

    //Se crea el getmapping
    @GetMapping("/listarproductocategoria/{categoria}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductosCategoria(@PathVariable Categoria categoria){
        //Se llama el servicio para listar las categorias y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosCategoria(categoria)));
    }

    //Se crea el getmapping
    @GetMapping("/listarproductousuario/{codigoUsuario}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductosUsuario(@PathVariable Integer codigoUsuario){
        //Se llama el servicio para listar los productos del usuario y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosUsuario(codigoUsuario)));
    }

    //Se crea el getmapping
    @GetMapping("/listarestado/{estado}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductosEstado(@PathVariable Estado estado){
        //Se llama el servicio para listar los productos por estado y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosEstado(estado)));
    }

    //Se crea el getmapping
    @GetMapping("/listarnombre/{nombre}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductosNombre(@PathVariable String nombre) {
        //Se llama el servicio para listar los productos por nombre y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosNombre(nombre)));
    }

    //Se crea el getmapping
    @GetMapping("/listarnombreprecio/{nombre}/{precioMin}/{precioMax}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductosPrecio(@PathVariable String nombre, @PathVariable float precioMin, @PathVariable float precioMax){
        //Se llama el servicio para listar por precio y por nombre y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosPrecio(nombre, precioMin, precioMax)));
    }

    //Se crea el getmapping
    @GetMapping("/listarproductosfavoritos/{codigoUsuario}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductosFavoritos(Integer codigoUsuario) throws Exception{
        //Se llama el servicio para listar los productos favoritos del usuario y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosFavoritos(codigoUsuario)));
    }

    //Se crea el postmapping
    @PostMapping("/crearfavorito/{codigoUsuario}/{codigoProducto}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> crearFavorito(@PathVariable int codigoUsuario, @PathVariable int codigoProducto) throws Exception{
        //Se llama el servicio para crear el favorito
        productoServicio.crearFavorito(codigoUsuario, codigoProducto);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "Se creó el favorito corractamente"));
    }

    //Se crea el deletemapping
    @DeleteMapping("/eliminarfavorito/{codigoUsuario}/{codigoProducto}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> eliminarFavorito(@PathVariable int codigoUsuario, @PathVariable int codigoProducto) throws Exception{
        //Se llama el servicio para eliminar el favorito
        productoServicio.eliminarFavorito(codigoUsuario, codigoProducto);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "Se eliminó el favorito correctamente"));
    }

    //Se crea el putmmaping
    @PutMapping("/actualizarunidades/{unidades}")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> actualizarUnidades(@Valid @RequestBody Producto producto,@PathVariable int unidades) throws Exception{
        //Se llama el servicio para actualizar las unidades del producto
        productoServicio.actualizarUnidades(producto, unidades);
        //Se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "Las unidades se actualizaron exitosamente"));
    }

    //Se crea el getmapping
    @GetMapping("/listarproductos")
    //se trae el metodo con sus parametros y se le hacen las anotaciones correspondientes
    public ResponseEntity<MensajeDTO> listarProductos() throws Exception{
        //Se llama el servicio para listar los productos y se retorna el body con la respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                productoServicio.listarProductos()));
    }

}
