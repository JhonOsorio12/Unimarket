package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.EmailDTO;
import co.edu.uniquindio.uniMarket.DTO.ProductoModeradorDTO;
import co.edu.uniquindio.uniMarket.entidades.*;
import co.edu.uniquindio.uniMarket.repositorios.ModeradorRepo;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private ProductoServicio productoServicio;

    //private ModeradorServicio moderadorServicio;

    private EmailServicio emailServicio;

    private final ModeradorRepo moderadorRepo;

    @Override
    public void validarProducto(ProductoModeradorDTO productoModeradorDTO) throws Exception {

        //Moderador moderador = moderadorServicio.obtener(productoModeradorDTO.getCodigoModerador());
        Producto producto = productoServicio.obtener(productoModeradorDTO.getCodigoProducto());
        ProductoModerador productoModerador = new ProductoModerador();
        productoModerador.setEstado(Estado.APROBADO);
        productoModerador.setMotivo(productoModeradorDTO.getMotivo());
        //productoModerador.setModeradorPM(productoModeradorDTO.getCodigoModerador());
        productoModerador.setProductoPM(producto);

        emailServicio.enviarEmail(new EmailDTO("Cambio de estado", "Su producto fue : " + productoModerador.getEstado()
                +" " +producto.getNombre()+ " " + productoModerador.getMotivo() ,
                 producto.getVendedor().getEmail()) );

    }

        @Override
        public void rechazarProducto(ProductoModeradorDTO productoModeradorDTO) throws Exception {

            //Moderador moderador = moderadorServicio.obtener(productoModeradorDTO.getCodigoModerador());
            Producto producto = productoServicio.obtener(productoModeradorDTO.getCodigoProducto());
            ProductoModerador productoModerador = new ProductoModerador();
            productoModerador.setEstado(Estado.RECHAZADO);
            productoModerador.setMotivo(productoModeradorDTO.getMotivo());
            //productoModerador.setModeradorPM(moderador);
            productoModerador.setProductoPM(producto);

            emailServicio.enviarEmail(new EmailDTO("Cambio de estado", "Su producto fue : " + productoModerador.getEstado()
                    +" "+ producto.getNombre()+ " " +  productoModerador.getMotivo() ,
                    producto.getVendedor().getEmail()) );

        }

    @Override
    public Moderador obtener(Integer codigoModerador) throws Exception {
        Optional<Moderador> moderador = moderadorRepo.findById(codigoModerador);

        if(moderador.isEmpty() ){
            throw new ResourceNotFoundException("El código "+codigoModerador+" no está asociado a ningún usuario");
        }

        return moderador.get();
    }

}
