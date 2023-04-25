package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.CentroAyudaDTO;
import co.edu.uniquindio.uniMarket.DTO.CentroAyudaGetDTO;
import co.edu.uniquindio.uniMarket.DTO.ComentarioGetDTO;
import co.edu.uniquindio.uniMarket.DTO.EmailDTO;
import co.edu.uniquindio.uniMarket.entidades.*;
import co.edu.uniquindio.uniMarket.repositorios.CentroAyudaRepo;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CentroAyudaServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CentroAyudaImpl implements CentroAyudaServicio {

    private UsuarioServicio usuarioServicio;

    private ModeradorServicio moderadorServicio;

    private EmailServicio emailServicio;

    private final CentroAyudaRepo centroAyudaRepo;

    @Override
    public int crearMensaje(CentroAyudaDTO centroAyudaDTO) throws Exception {

        Moderador moderador = moderadorServicio.obtener(centroAyudaDTO.getCodigoModerador());
        Usuario usuario = usuarioServicio.obtener(centroAyudaDTO.getCodigoUsuario());

        CentroAyuda centroAyuda = new CentroAyuda();
        centroAyuda.setMensaje(centroAyudaDTO.getMensaje());
        centroAyuda.setFecha(LocalDate.now());
        centroAyuda.setUsuario(usuario);
        centroAyuda.setModerador(moderador);

        emailServicio.enviarEmail(new EmailDTO("Centro de ayuda", "Se hizo una solicitud: "
                + centroAyuda.getCodigoAyuda() + centroAyuda.getMensaje()
                + centroAyuda.getUsuario().getNombre(), centroAyuda.getModerador().getEmail()) );

        return centroAyudaRepo.save(centroAyuda).getCodigoAyuda();
    }

    @Override
    public List<CentroAyudaGetDTO> listarMensajesCentroAyuda(Integer codigoCentroAyuda) throws Exception {

        List<CentroAyuda> lista = centroAyudaRepo.listarMensajesCentroAyuda(codigoCentroAyuda);
        List<CentroAyudaGetDTO> respuesta = new ArrayList<>();

        for (CentroAyuda centroAyuda : lista) {
            respuesta.add(convertir(centroAyuda));
        }

        return respuesta;
    }

    @Override
    public CentroAyudaGetDTO obtenerCentroAyuda(Integer codigoCentroAyuda) throws Exception {
        Optional<CentroAyuda> centroAyuda = centroAyudaRepo.findById(codigoCentroAyuda);

        if (centroAyuda.isEmpty()){
            throw new Exception("El código "+codigoCentroAyuda+" no está asociado a ningún mensaje");
        }

        return convertir(centroAyuda.get());
    }

    private CentroAyudaGetDTO convertir(CentroAyuda centroAyuda){

        CentroAyudaGetDTO centroAyudaDTO = new CentroAyudaGetDTO(
                centroAyuda.getCodigoAyuda(),
                centroAyuda.getMensaje(),
                centroAyuda.getFecha(),
                centroAyuda.getUsuario().getCodigo(),
                centroAyuda.getModerador().getCodigo()
        );
        return centroAyudaDTO;
    }

}
