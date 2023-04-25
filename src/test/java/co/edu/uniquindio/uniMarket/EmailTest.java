package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.EmailDTO;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class EmailTest {

    @Autowired
    private EmailServicio emailServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void enviarEmailTest() throws Exception{

        EmailDTO emailDTO = new EmailDTO(
                "Test",
                "Bienvenido a unimarket",
                "jhone.vargaso@uqvirtual.edu.co"
        );

         emailServicio.enviarEmail(emailDTO);

        Assertions.assertEquals("Test", emailDTO.getAsunto());

    }

}
