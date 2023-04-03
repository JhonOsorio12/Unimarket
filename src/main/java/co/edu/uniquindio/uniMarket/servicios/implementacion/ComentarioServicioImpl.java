package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.ComentarioDTO;
import co.edu.uniquindio.uniMarket.DTO.ComentarioGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Comentario;
import co.edu.uniquindio.uniMarket.repositorios.ComentarioRepo;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    private ProductoServicio productoServicio;

    private UsuarioServicio usuarioServicio;

    @Override
    public int crearComentario(ComentarioDTO comentarioDTO) throws Exception {

        Comentario comentario = new Comentario();
        comentario.setMensaje(comentarioDTO.getMensaje());
        comentario.setProductoCOM(productoServicio.obtener(comentarioDTO.getCodigoProducto()));
        comentario.setUsuarioCOM(usuarioServicio.obtener(comentarioDTO.getCodigoUsuario()));
        comentario.setFechaCreacion(LocalDateTime.now());

        return comentarioRepo.save(comentario).getCodigo();
    }

    @Override
    public List<ComentarioGetDTO> listarComentarios(Integer codigoProducto) {

        List<Comentario> lista = comentarioRepo.listarComentarios(codigoProducto);
        List<ComentarioGetDTO> respuesta = new ArrayList<>();

        for (Comentario c : lista){
            respuesta.add(convertir(c));
        }

        return respuesta;
    }

    private ComentarioGetDTO convertir(Comentario comentario){

        ComentarioGetDTO comentarioDTO = new ComentarioGetDTO(
                comentario.getCodigo(),
                comentario.getMensaje(),
                comentario.getProductoCOM().getCodigo(),
                comentario.getUsuarioCOM().getCodigo(),
                comentario.getFechaCreacion()
        );
        return comentarioDTO;
    }


}
