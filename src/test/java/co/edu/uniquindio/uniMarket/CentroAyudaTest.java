package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.CentroAyudaDTO;
import co.edu.uniquindio.uniMarket.DTO.CentroAyudaGetDTO;
import co.edu.uniquindio.uniMarket.DTO.CompraGetDTO;
import co.edu.uniquindio.uniMarket.entidades.CentroAyuda;
import co.edu.uniquindio.uniMarket.entidades.MetodoPago;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CentroAyudaServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class CentroAyudaTest {

    @Autowired
    private CentroAyudaServicio centroAyudaServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearMensajeTest() throws Exception{

        CentroAyudaDTO centroAyudaDTO = new CentroAyudaDTO(
                "Hola, por favor apruebe mis productos",
                LocalDateTime.now(),
                1,
                2
        );

        int centroAyuda = centroAyudaServicio.crearMensaje(centroAyudaDTO);
        Assertions.assertEquals(centroAyuda, centroAyudaServicio.obtenerCentroAyuda(centroAyuda).getCodigo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarMensajesCentroAyudaTest() throws Exception {

        List<CentroAyudaGetDTO> centroAyudaGetDTO = centroAyudaServicio.listarMensajesCentroAyuda(1);
        centroAyudaGetDTO.forEach(System.out::println);

        Assertions.assertEquals(1, centroAyudaGetDTO.size());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCentroAyuda() throws Exception{
        CentroAyudaGetDTO centroAyudaGetDTO = centroAyudaServicio.obtenerCentroAyuda(1);

        Assertions.assertEquals("El producto aun no llega, que pasa??", centroAyudaGetDTO.getMensaje());
    }

}
