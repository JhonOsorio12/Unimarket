package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.CentroAyudaGetDTO;
import co.edu.uniquindio.uniMarket.DTO.ComentarioDTO;
import co.edu.uniquindio.uniMarket.DTO.ComentarioGetDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ComentarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class ComentarioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearComentarioTest() throws Exception{

        ComentarioDTO comentarioDTO = new ComentarioDTO(
                "Un producto muy bueno",
                1,
                1

        );

        int nuevoComentario = comentarioServicio.crearComentario(comentarioDTO);
        Assertions.assertNotNull(nuevoComentario);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarlistarComentariosTest() throws Exception{
        List<ComentarioGetDTO> comentarioGetDTO = comentarioServicio.listarComentarios(1);
        comentarioGetDTO.forEach(System.out::println);

        Assertions.assertEquals(1, comentarioGetDTO.size());
    }

}
