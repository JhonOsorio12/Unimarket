package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepo extends JpaRepository<Calificacion, Integer> {

    //Esta consulta obtiene la calificación de un usuario ingresando su codigo
    @Query("select c from Calificacion c where c.usuario.codigo = :codigo")
    List<Calificacion> obtenerCalificacionesUsuarioPorCedula(Integer codigo);

    // Esta consulta valida el promedio de las calificaciones de cada producto ingresando su nombre
    @Query("select avg(c.puntuacion) from Calificacion c where c.producto.nombre = :nombre")
    Double obtenerPromedioCalificacionPelicula(String nombre);

}
