package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.ProductoModerador;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ModeradorServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ModeradorServicioImpl implements ModeradorServicio {
    @Override
    public void validarProducto(ProductoModeradorDTO producto) throws Exception {

        ProductoModerador productoModerador = new ProductoModerador();
        productoModerador.setEstado(Estado.APROBADO);
        productoModerador.setMotivo(productoModerador.getMotivo());
        productoModerador.setModeradorPM(productoModerador.getModeradorPM());
        productoModerador.setProductoPM(productoModerador.getProductoPM());
        productoModerador.setFecha(LocalDate.now().plusDays(60));

    }

        @Override
        public void rechazarProducto(ProductoModeradorDTO producto) throws Exception {

            ProductoModerador productoModerador = new ProductoModerador();
            productoModerador.setEstado(Estado.RECHAZADO);
            productoModerador.setMotivo(productoModerador.getMotivo());
            productoModerador.setModeradorPM(productoModerador.getModeradorPM());
            productoModerador.setProductoPM(productoModerador.getProductoPM());
        }

}
