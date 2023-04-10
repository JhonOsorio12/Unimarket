package co.edu.uniquindio.uniMarket.security.servicios;

import co.edu.uniquindio.uniMarket.entidades.Moderador;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.ModeradorRepo;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.security.modelo.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepo usuarioRepo;

    private final ModeradorRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);

        if (usuario.isEmpty()){
            Optional<Moderador> admin = adminRepo.findByEmail(email);

            if (admin.isEmpty())
                throw new UsernameNotFoundException("El usuario no existe");

            return UserDetailsImpl.build(admin.get());
        }else {
            return UserDetailsImpl.build(usuario.get());
        }


    }
}
