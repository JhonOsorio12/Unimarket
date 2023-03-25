package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.entidades.Estado;

import java.util.List;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO) throws Exception;

    int eliminarProducto(Integer codigoProducto) throws Exception;

    int actualizarProducto(Integer codigoProducto, ProductoDTO productoDTO) throws Exception;

    int actualizarPorEstado(Integer codigoProducto, Estado estado) throws Exception;

    int actualizarPorCantidad(Integer codigoProducto, Integer unidades) throws Exception;

    ProductoGetDTO obtenerProducto(Integer codigoProducto) throws Exception;

    List<ProductoGetDTO>listarProductosCategoria(Categoria categoria);

    List<ProductoGetDTO>listarProductosUsuario(Integer codigoUsuario);

    List<ProductoGetDTO>listarProductosEstado(Estado estado);

    List<ProductoGetDTO>listarProductosNombre(String nombre);

    List<ProductoGetDTO>listarProductosPrecio(float precioMin, float precioMax);

    List<ProductoGetDTO> listarFavoritosUsuario(Integer codigoUsuario);


}
