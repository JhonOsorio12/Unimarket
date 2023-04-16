package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.Moderador;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, Integer> {

    @Query("select p from Producto p join ProductoModerador pm where pm.estado = :estado")
    List<Moderador> listarProductosPorEstado(Estado estado);

    Optional<Moderador> findByEmail(String email);

}
