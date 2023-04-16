package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario and p.fechaLimite < current_date")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    //List<Producto> findByVendedor(Usuario vendedor);

    @Query("select p from Producto p where :categoria member of p.categoria and p.fechaLimite <= current_date " +
            "and p.activo = co.edu.uniquindio.uniMarket.entidades.Activo.ACTIVO")
    List<Producto> listarProductosCategoria(Categoria categoria);

    //@Query("update Producto p set p. =: codigoProducto ")
    //Producto actualizarPorEstado(int codigoProducto, Estado estado);

    @Query("select p from Producto p join p.productoModerador pm where pm.estado = :estado and p.fechaLimite < current_date")
    List<Producto> listarProductosEstado(Estado estado);


    @Query("select p from Producto p where p.nombre like concat( '%', :nombre,  '%' ) and p.fechaLimite < current_date and p.activo = :activo" )
    List<Producto> listarProductosNombre(String nombre, Activo activo);

    @Query("select p from Producto p where p.nombre like concat( '%', :nombre, '%' ) and p.fechaLimite < current_date and p.precio > :precioMin and p.precio < :precioMax")
    List<Producto> listarProductosPrecio(String nombre, float precioMin, float precioMax, Sort sort);

    @Query("select f from Usuario u join u.favoritos f where u.codigo = :codigoUsuario " +
            "and f.activo = co.edu.uniquindio.uniMarket.entidades.Activo.ACTIVO")
    List<Producto> listarProductosFavoritos(int codigoUsuario);

    @Query("select p from Producto p where p.fechaLimite <= current_date and p.activo = co.edu.uniquindio.uniMarket.entidades.Estado.APROBADO")
    List<Producto> listarProductos();

    Optional<Producto> findById(int codigoProducto);

}
