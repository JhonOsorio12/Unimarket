package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.ProductoDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Activo;
import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.Producto;

import java.util.List;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO) throws Exception;

    int eliminarProducto(Integer codigoProducto) throws Exception;

    ProductoGetDTO actualizarProducto(Integer codigoProducto, ProductoDTO productoDTO) throws Exception;

    int actualizarPorEstado(Integer codigoProducto, Activo estado) throws Exception;

    int actualizarPorCantidad(Integer codigoProducto, Integer unidades) throws Exception;

    ProductoGetDTO obtenerProducto(Integer codigoProducto) throws Exception;

    Producto obtener(Integer codigoProducto)throws Exception;

    List<ProductoGetDTO>listarProductosCategoria(Categoria categoria);

    List<ProductoGetDTO>listarProductosUsuario(Integer codigoUsuario);

    List<ProductoGetDTO>listarProductosEstado(Estado estado);

    List<ProductoGetDTO>listarProductosNombre(String nombre);

    List<ProductoGetDTO>listarProductosPrecio(float precioMin, float precioMax);

    List<ProductoGetDTO> listarFavoritosUsuario(Integer codigoUsuario);


}
