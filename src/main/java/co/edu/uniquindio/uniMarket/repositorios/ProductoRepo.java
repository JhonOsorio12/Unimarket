package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    //List<Producto> findByVendedor(Usuario vendedor);

    @Query("select p from Producto p join p.categoria c where c = :categoria")
    List<Producto> listarProductosCategoria(Categoria categoria);

    //@Query("update Producto p set p. =: codigoProducto ")
    //Producto actualizarPorEstado(int codigoProducto, Estado estado);

    @Query("select p from Producto p join p.productoModerador pm where pm.estado = :estado")
    List<Producto> listarProductosEstado(Estado estado);


    @Query("select p from Producto p where p.nombre like concat( '%', :nombre,  '%' ) and p.estado = :activo" )
    List<Producto> listarProductosNombre(String nombre, Activo activo);

    @Query("select p from Producto p where p.precio > :precioMin and p.precio < :precioMax")
    List<Producto> listarProductosPrecio(float precioMin, float precioMax, Sort sort);

}
