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

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) throws Exception{
            productoServicio.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "El producto se creó exitosamente"));
    }

    @PutMapping("/actualizar/{codigoProducto}")
    public ResponseEntity<MensajeDTO> actualizarProducto(@PathVariable Integer codigoProducto, @Valid @RequestBody ProductoDTO productoDTO) throws Exception{
            productoServicio.actualizarProducto(codigoProducto, productoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El producto se actualizó exitosamente"));
    }

    @DeleteMapping("/eliminar/{codigoProducto}")
    public ResponseEntity<MensajeDTO> eliminarProducto(int codigoProducto) throws Exception{
            productoServicio.eliminarProducto(codigoProducto);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El producto se actualizó exitosamente"));
    }

    @GetMapping("/obtener/{codigoProducto}")
    public ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable int codigoProducto) throws Exception{
            productoServicio.obtenerProducto(codigoProducto);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "Se obtuvo el producto correctamente"));
    }

    @PutMapping("/actualizar/{activo}")
    public ResponseEntity<MensajeDTO> actualizarPorEstado(@Valid @RequestBody Producto producto, Activo activo) throws Exception{
            productoServicio.actualizarPorEstado(producto, activo);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                "El estado se actualizó exitosamente"));
    }

    @GetMapping("/listar/{categoria}")
    public ResponseEntity<MensajeDTO> listarProductosCategoria(@PathVariable Categoria categoria){

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosCategoria(categoria)));
    }


    @GetMapping("/listar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> listarProductosUsuario(@PathVariable Integer codigoUsuario){

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosUsuario(codigoUsuario)));
    }

    @GetMapping("/listar/{estado}")
    public ResponseEntity<MensajeDTO> listarProductosEstado(@PathVariable Estado estado){

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosEstado(estado)));
    }

    @GetMapping("/listar/{nombre}")
    public ResponseEntity<MensajeDTO> listarProductosNombre(@PathVariable String nombre) {

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosNombre(nombre)));
    }

    @GetMapping("/listar/{nombre}/{precioMin}/{precioMax}")
    public ResponseEntity<MensajeDTO> listarProductosPrecio(@PathVariable String nombre, @PathVariable float precioMin, @PathVariable float precioMax){

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosPrecio(nombre, precioMin, precioMax)));
    }

    @GetMapping("/listar/{}")
    public ResponseEntity<MensajeDTO> listarProductosFavoritos(Integer codigoUsuario) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(
                HttpStatus.OK,
                false,
                productoServicio.listarProductosFavoritos(codigoUsuario)));
    }

    @PostMapping("/crear/{codigoUsuario}/{codigoProducto}")
    public ResponseEntity<MensajeDTO> crearFavorito(@PathVariable int codigoUsuario, @PathVariable int codigoProducto) throws Exception{
            productoServicio.crearFavorito(codigoUsuario, codigoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "Se creó el favorito corractamente"));
    }

    @DeleteMapping("/eliminar/{codigoUsuario}/{codigoProducto}")
    public ResponseEntity<MensajeDTO> eliminarFavorito(@PathVariable int codigoUsuario, @PathVariable int codigoProducto) throws Exception{
        productoServicio.eliminarFavorito(codigoUsuario, codigoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "Se eliminó el favorito correctamente"));
    }

    @PutMapping("/actualizar/{unidades}")
    public ResponseEntity<MensajeDTO> actualizarUnidades(Producto producto, int unidades) throws Exception{
            productoServicio.actualizarUnidades(producto, unidades);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                "Las unidades se actualizaron exitosamente"));
    }

    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listarProductos() throws Exception{

        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
                HttpStatus.CREATED,
                false,
                productoServicio.listarProductos()));
    }

}
