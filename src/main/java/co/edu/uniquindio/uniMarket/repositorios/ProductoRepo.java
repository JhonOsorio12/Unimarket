package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario and p.fechaLimite < current_date")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    //List<Producto> findByVendedor(Usuario vendedor);

    @Query("select p from Producto p join p.productoModerador pm where :categoria member of p.categoria and p.fechaLimite <= current_date " +
            "and pm.estado = co.edu.uniquindio.uniMarket.entidades.Estado.APROBADO")
    List<Producto> listarProductosCategoria(Categoria categoria);

    //@Query("update Producto p set p. =: codigoProducto ")
    //Producto actualizarPorEstado(int codigoProducto, Estado estado);

    @Query("select p from Producto p join p.productoModerador pm where pm.estado = :estado and p.fechaLimite < current_date")
    List<Producto> listarProductosEstado(Estado estado);


    @Query("select p from Producto p join p.productoModerador pm where p.nombre like concat( '%', :nombre,  '%' ) and p.fechaLimite < current_date" +
            " and pm.estado = co.edu.uniquindio.uniMarket.entidades.Estado.APROBADO")
    List<Producto> listarProductosNombre(String nombre);

    @Query("select p from Producto p join p.productoModerador pm where p.nombre like concat( '%', :nombre, '%' ) and p.fechaLimite < current_date and" +
            " p.precio > :precioMin and p.precio < :precioMax and pm.estado = co.edu.uniquindio.uniMarket.entidades.Estado.APROBADO")
    List<Producto> listarProductosPrecio(String nombre, float precioMin, float precioMax, Sort sort);

    @Query("select f from Usuario u join u.favoritos f join f.productoModerador pm  " +
           " where f.fechaLimite < current_date and u.codigo = :codigoUsuario " +
            "and pm.estado = co.edu.uniquindio.uniMarket.entidades.Estado.APROBADO")
    List<Producto> listarProductosFavoritos(int codigoUsuario);

    @Query("select p from Producto p join p.productoModerador pm where p.fechaLimite <= current_date and" +
            " pm.estado = co.edu.uniquindio.uniMarket.entidades.Estado.APROBADO")
    List<Producto> listarProductos();

    Optional<Producto> findById(int codigoProducto);


    // Esta consulta válida sí existe el producto dado el nombre del producto
    @Query("select p from Producto p where p.nombre = :nombre")
    Producto buscarProductoPorNombre(String nombre);

    @Query("select dc.productoDT.codigo from Compra c join DetalleCompra dc where c.usuario.codigo = :codigoUsuario")
    List<Producto> listarProductosXUsuario(Integer codigoUsuario);

    /*
    @Query("select p.codigo,  max(p.precio), min(p.precio) from Producto p where p.categoria = :categoria")
    Producto obtenerProductoMasCaroMasBarato(Categoria categoria, Pageable pg);
    */

    @Query("select p.categoria ,count(p.codigo) from Producto p group by p.categoria")
    List<Categoria> contarCantidadProductoCategoria();


    @Query("select SUM(c.valorTotal) from Compra c where month(c.fechaCreacion) = :mes and year(c.fechaCreacion) = :anio")
    double listarValorVentas(int mes , int anio);

}
