package co.edu.uniquindio.uniMarket;

import co.edu.uniquindio.uniMarket.DTO.*;
import co.edu.uniquindio.uniMarket.entidades.*;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CompraServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class CompraTest {

    @Autowired
    private CompraServicio compraServicio;



    @Test
    @Sql("classpath:dataset.sql")
    public void crearCompraTest() throws Exception{

        List<DetalleCompraDTO> dt = new ArrayList<>();
        dt.add(new DetalleCompraDTO(1,2,200000));
        dt.add(new DetalleCompraDTO(3,3,100000));


        CompraDTO compraDTO = new CompraDTO(
                2,
                MetodoPago.EFECTIVO,
                dt

        );

        try {
            int compra = compraServicio.crearCompra2(compraDTO);
            Assertions.assertThrows( Exception.class, () -> compraServicio.obtenerCompra(1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompraTest() throws Exception{
        try {
            CompraGetDTO compraGetDTO = compraServicio.obtenerCompra(1);
            Assertions.assertEquals(1, compraGetDTO.getMetodoPago());
        }catch (Exception e){
            throw new ResourceNotFoundException("Compra no encontrada");
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasUsuarioTest() throws Exception{
        try {
            List<CompraGetDTO> compraGetDTO = compraServicio.listarComprasUsuario(1);
            compraGetDTO.forEach(System.out::println);
            Assertions.assertEquals(1, compraGetDTO.size());
        }catch (Exception e){
            throw new ResourceNotFoundException("La cantidad no coincide");
        }
    }



}
