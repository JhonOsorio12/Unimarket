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

    int eliminarProducto(int codigoProducto) throws Exception;

    ProductoGetDTO actualizarProducto(Integer codigoProducto, ProductoDTO productoDTO) throws Exception;

    int actualizarPorEstado(Producto producto, Activo activo) throws Exception;

    int actualizarPorCantidad(Integer codigoProducto, Integer unidades) throws Exception;

    ProductoGetDTO obtenerProducto(int codigoProducto) throws Exception;

    Producto obtener(int codigoProducto)throws Exception;

    List<ProductoGetDTO>listarProductosCategoria(Categoria categoria);

    List<ProductoGetDTO>listarProductosUsuario(Integer codigoUsuario);

    List<ProductoGetDTO>listarProductosEstado(Estado estado);

    List<ProductoGetDTO>listarProductosNombre(String nombre);

    List<ProductoGetDTO>listarProductosPrecio(String nombre, float precioMin, float precioMax);

    List<ProductoGetDTO> listarProductosFavoritos(Integer codigoUsuario) throws Exception;

    void crearFavorito(int codigoUsuario, int codigoProducto) throws Exception;

    void eliminarFavorito(int codigoUsuario, int codigoProducto) throws Exception;

    List<ProductoGetDTO> listarProductos() throws Exception;

    int actualizarUnidades(Producto producto, int unidades) throws Exception;
}
