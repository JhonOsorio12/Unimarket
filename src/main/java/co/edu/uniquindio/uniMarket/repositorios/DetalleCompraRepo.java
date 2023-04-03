package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {

    @Query("select dt from DetalleCompra dt where dt.compraDT.codigo = :codigoCompra")
    List<DetalleCompra> listarDetalleCompra(Integer codigoCompra);


}
