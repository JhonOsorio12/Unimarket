package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoGetDTO;
import co.edu.uniquindio.uniMarket.entidades.*;
import co.edu.uniquindio.uniMarket.repositorios.ProductoModeRepo;
import co.edu.uniquindio.uniMarket.repositorios.ProductoRepo;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    private final UsuarioServicio usuarioServicio;

    //private final ProductoModeRepo productoModeRepo;

    @Override
    public int crearProducto(ProductoDTO productoDTO) throws Exception {

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setUnidades(productoDTO.getUnidades());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setVendedor(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
        producto.setImagen(productoDTO.getImagenes());
        producto.setCategoria(productoDTO.getCategorias());
        producto.setActivo(Activo.INACTIVO);
        producto.setFechaCreado(LocalDateTime.now());
        producto.setFechaLimite(LocalDateTime.now().plusDays(60));

        return productoRepo.save(producto).getCodigo();

    }

    @Override
    public int eliminarProducto(int codigoProducto) throws Exception {

        obtenerProducto(codigoProducto);
        productoRepo.deleteById(codigoProducto);

        return codigoProducto;
    }

    @Override
    public ProductoGetDTO actualizarProducto(Integer codigoProducto, ProductoDTO productoDTO) throws Exception {

        Producto producto = obtener(codigoProducto);

        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setNombre(productoDTO.getNombre());
        producto.setUnidades(productoDTO.getUnidades());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setVendedor(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
        producto.setImagen(productoDTO.getImagenes());
        producto.setCategoria(productoDTO.getCategorias());

        return convertir(producto);
    }

    private void validarExiste(int codigoProducto) throws Exception{
        boolean existe = productoRepo.existsById(codigoProducto);

        if( !existe ){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún producto");
        }

    }

    @Override
    public int actualizarPorEstado(Integer codigoProducto, Activo activo) throws Exception {
        validarExiste(codigoProducto);
        Producto producto = obtener(codigoProducto);
        producto.setActivo(activo);
        productoRepo.save(producto);
        return codigoProducto;
    }

    @Override
    public int actualizarPorCantidad(Integer codigoProducto, Integer unidades) throws Exception {

        validarExiste(codigoProducto);
        Producto producto = obtener(codigoProducto);
        producto.setUnidades(unidades);
        productoRepo.save(producto);
        return codigoProducto;
    }

    @Override
    public ProductoGetDTO obtenerProducto(int codigoProducto) throws Exception {

        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if (producto.isEmpty()){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún producto");
        }

        return convertir(obtener(codigoProducto));
    }

    public Producto obtener(int codigoProducto) throws Exception {

        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if (producto.isEmpty()){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún producto");
        }

        return producto.get();

    }

    @Override
    public List<ProductoGetDTO> listarProductosCategoria(Categoria categoria) {

        List<Producto> lista = productoRepo.listarProductosCategoria(categoria);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosUsuario(Integer codigoUsuario) {

        List<Producto> lista = productoRepo.listarProductosUsuario(codigoUsuario);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    private ProductoGetDTO convertir(Producto producto){

        ProductoGetDTO productoDTO = new ProductoGetDTO(
                producto.getCodigo(),
                producto.getActivo(),
                producto.getDescripcion(),
                producto.getFechaCreado(),
                producto.getFechaLimite(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getUnidades(),
                producto.getImagen(),
                producto.getCategoria(),
                producto.getVendedor().getCodigo()
        );

        return productoDTO;

    }

    /*
    private Producto convertir(ProductoDTO productoDTO) throws Exception{

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setUnidades(productoDTO.getUnidades());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setVendedor(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
        producto.setImagen(productoDTO.getImagenes());
        producto.setCategoria(productoDTO.getCategorias());

        return producto;

    }
    */
    @Override
    public List<ProductoGetDTO> listarProductosEstado(Estado estado) {

        List<Producto> lista = productoRepo.listarProductosEstado(estado);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosNombre(String nombre) {

        List<Producto> lista = productoRepo.listarProductosNombre(nombre, Activo.ACTIVO);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosPrecio(float precioMin, float precioMax) {

        List<Producto> lista = productoRepo.listarProductosPrecio(precioMin, precioMax, Sort.by(Sort.Direction.DESC, "precio"));

        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarFavoritosUsuario(Integer codigoUsuario) {
        return null;
    }
}
