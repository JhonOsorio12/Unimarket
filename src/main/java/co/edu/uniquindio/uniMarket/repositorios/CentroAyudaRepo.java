package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.CentroAyuda;
import co.edu.uniquindio.uniMarket.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroAyudaRepo extends JpaRepository<CentroAyuda, Integer> {

    @Query("select ca from CentroAyuda ca where ca.codigoAyuda = :codigoCentroAyuda")
    List<CentroAyuda> listarMensajesCentroAyuda(Integer codigoCentroAyuda);

}
