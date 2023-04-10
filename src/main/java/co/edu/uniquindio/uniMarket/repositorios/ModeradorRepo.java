package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Moderador;
import co.edu.uniquindio.uniMarket.entidades.Usuario;

import java.util.Optional;

public interface ModeradorRepo {

    Optional<Moderador> findByEmail(String email);

}
