package co.edu.uniquindio.uniMarket;


import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.ProductoModerador;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ModeradorServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class ModeradorTest {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void validarProductoTest() throws Exception {

        ProductoModeradorDTO productoModeradorDTO = new ProductoModeradorDTO(
                "Cumple con todo",
                Estado.APROBADO,
                1,
                1
        );

        moderadorServicio.validarProducto(productoModeradorDTO);
        Assertions.assertEquals(Estado.APROBADO, productoModeradorDTO.getEstado());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void rechazarProductoTest() throws Exception{

        ProductoModeradorDTO productoModeradorDTO = new ProductoModeradorDTO(
                "No cumple con todo",
                Estado.RECHAZADO,
                2,
                2
        );

        moderadorServicio.rechazarProducto(productoModeradorDTO);
        Assertions.assertEquals(Estado.RECHAZADO, productoModeradorDTO.getEstado());

    }

}
