package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.EmailDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.ProductoModerador;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private ProductoServicio productoServicio;

    private EmailServicio emailServicio;

    @Override
    public void validarProducto(ProductoModeradorDTO productoModeradorDTO) throws Exception {

        Producto producto = productoServicio.obtener(productoModeradorDTO.getCodigoProducto());
        ProductoModerador productoModerador = new ProductoModerador();
        productoModerador.setEstado(Estado.APROBADO);
        productoModerador.setMotivo(productoModerador.getMotivo());
        productoModerador.setModeradorPM(productoModerador.getModeradorPM());
        productoModerador.setProductoPM(producto);

        emailServicio.enviarEmail(new EmailDTO("Cambio de estado", "Su producto fue : " + productoModerador.getEstado()
                + productoModerador.getCodigo() + productoModerador.getMotivo() ,
                 producto.getVendedor().getEmail()) );

    }

        @Override
        public void rechazarProducto(ProductoModeradorDTO productoModeradorDTO) throws Exception {

            Producto producto = productoServicio.obtener(productoModeradorDTO.getCodigoProducto());
            ProductoModerador productoModerador = new ProductoModerador();
            productoModerador.setEstado(Estado.RECHAZADO);
            productoModerador.setMotivo(productoModerador.getMotivo());
            productoModerador.setModeradorPM(productoModerador.getModeradorPM());
            productoModerador.setProductoPM(producto);

            emailServicio.enviarEmail(new EmailDTO("Cambio de estado", "Su producto fue : " + productoModerador.getEstado()
                    + productoModerador.getCodigo() + productoModerador.getMotivo() ,
                    producto.getVendedor().getEmail()) );

        }

}
