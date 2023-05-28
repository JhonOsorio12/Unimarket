package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CategoriaSevicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaServicioImpl implements CategoriaSevicio {
    @Override
    public List<Categoria> listarCategoria() throws Exception {

        List<Categoria> lista = List.of( Categoria.values() );

        return lista;
    }
}
