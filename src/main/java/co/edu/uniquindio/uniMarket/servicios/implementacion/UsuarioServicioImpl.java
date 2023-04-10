package co.edu.uniquindio.uniMarket.servicios.implementacion;


import co.edu.uniquindio.uniMarket.DTO.CuentaPremiumDTO;
import co.edu.uniquindio.uniMarket.DTO.EmailDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioDTO;
import co.edu.uniquindio.uniMarket.DTO.UsuarioGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    private EmailServicio emailServicio;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo){
        this.usuarioRepo=usuarioRepo;
    }

    @Override
    public int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception {

        Usuario buscado = usuarioRepo.buscarUsuario(usuarioDTO.getEmail());

        if(buscado!=null){
            throw new Exception("El correo "+usuarioDTO.getEmail()+" ya está en uso");
        }

        Usuario usuario = convertir(usuarioDTO);

        emailServicio.enviarEmail(new EmailDTO("Registro de cuenta a Unimarket", "Bienvenido a Unimarket "+usuarioDTO.getNombre(), "Correo destino"+usuarioDTO.getEmail()));

        return usuarioRepo.save( usuario ).getCodigo();
    }

    @Override
    public UsuarioGetDTO actualizarUsuario(int codigoUsuario,UsuarioDTO usuarioDTO) throws Exception {

        Usuario buscado = usuarioRepo.buscarUsuario(usuarioDTO.getEmail(), codigoUsuario);

        if (buscado!=null){
            throw new Exception("El correo "+usuarioDTO.getEmail()+" ya está en uso");
        }

        validarExiste(codigoUsuario);

        Usuario usuario = convertir(usuarioDTO);
        usuario.setCodigo(codigoUsuario);

        return convertir(usuarioRepo.save(usuario));

    }

    @Override
    public int eliminarUsuario(Integer codigoUsuario) throws Exception {
        obtenerUsuario(codigoUsuario);
        usuarioRepo.deleteById(codigoUsuario);
        return codigoUsuario;
    }

    @Override
    public UsuarioGetDTO obtenerUsuario(Integer codigoUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if (usuario.isEmpty()){
            throw new Exception("El código del usuario no existe");
        }

        return convertir( obtener(codigoUsuario) );

    }

    public Usuario obtener(Integer codigoUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if(usuario.isEmpty() ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

        return usuario.get();
    }

    /*
    public boolean estaDisponible(String email){
        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
        return usuario.isEmpty();
    }

    */

    private UsuarioGetDTO convertir(Usuario usuario){

        UsuarioGetDTO usuarioDTO = new UsuarioGetDTO(
                usuario.getCodigo(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getDireccion(),
                usuario.getTelefono());

        return usuarioDTO;
    }

    private void validarExiste(int codigoUsuario) throws Exception{
        boolean existe = usuarioRepo.existsById(codigoUsuario);

        if( !existe ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

    }

    private Usuario convertir(UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();
        usuario.setNombre( usuarioDTO.getNombre() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setDireccion( usuarioDTO.getDireccion() );
        usuario.setTelefono( usuarioDTO.getTelefono() );
        usuario.setPassword( usuarioDTO.getPassword() );

        return usuario;
    }



    @Override
    public int marcarFavorito(Integer codigoUsuario, Integer codigoProducto) {
        return 0;
    }

    @Override
    public int eliminarFavorito(Integer codigoUsuario, Integer codigoProducto) {
        return 0;
    }

    @Override
    public int crearCuentaPremium(CuentaPremiumDTO cuentaPremiumDTO) {

        return 0;

    }
}
