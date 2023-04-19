package co.edu.uniquindio.uniMarket.servicios.implementacion;


import co.edu.uniquindio.uniMarket.DTO.*;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    private final EmailServicio emailServicio;

    private final PasswordEncoder passwordEncoder;

    @Override
    public int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception {

        Usuario buscado = usuarioRepo.buscarUsuario(usuarioDTO.getEmail());

        if(buscado!=null){
            throw new ResourceNotFoundException("El correo "+usuarioDTO.getEmail()+" ya está en uso");
        }

        Usuario usuario = convertir(usuarioDTO);
        usuario.setPassword( passwordEncoder.encode(usuario.getPassword()));

        Usuario usuario1 = usuarioRepo.save( usuario );

        emailServicio.enviarEmail(new EmailDTO("Registro de cuenta a Unimarket", "Bienvenido a Unimarket "
                +usuarioDTO.getNombre(), "jhone.vargaso@uqvirtual.edu.co"));

        return usuario1.getCodigo();
    }

    @Override
    public UsuarioGetDTO actualizarUsuario(int codigoUsuario,UsuarioDTO usuarioDTO) throws Exception {

        Usuario buscado = usuarioRepo.buscarUsuario(usuarioDTO.getEmail());

        if (buscado!=null){
            throw new ResourceNotFoundException("El correo "+usuarioDTO.getEmail()+" ya está en uso");
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
            throw new ResourceNotFoundException("El código del usuario no existe");
        }

        return convertir( obtener(codigoUsuario) );

    }

    @Override
    public Usuario obtener(Integer codigoUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if(usuario.isEmpty() ){
            throw new ResourceNotFoundException("El código "+codigoUsuario+" no está asociado a ningún usuario");
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

    public void validarExiste(int codigoUsuario) throws Exception{
        boolean existe = usuarioRepo.existsById(codigoUsuario);

        if( !existe ){
            throw new ResourceNotFoundException("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

    }

    public void enviarLinkRecuperacion(String correo) throws Exception{
        emailServicio.enviarEmail(new EmailDTO("Cambio de contraseña", "Para cambiar la contraseña ingrese a: [link]/"+correo, correo));
    }

    @Override
    public void cambiarPassword(String email, String passwordNueva) throws Exception {
        Usuario personaEncontrada = usuarioRepo.buscarUsuario(email);

        if (personaEncontrada != null){
            personaEncontrada.setPassword(passwordEncoder.encode(passwordNueva));
            usuarioRepo.save(personaEncontrada);
        } else {
            throw new ResourceNotFoundException("No existe el usuario con el correo dado");
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
    public int crearCuentaPremium(CuentaPremiumDTO cuentaPremiumDTO) {

        return 0;
    }



}
