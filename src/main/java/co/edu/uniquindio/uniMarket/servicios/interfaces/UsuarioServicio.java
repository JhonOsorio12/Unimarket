package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.*;
import co.edu.uniquindio.uniMarket.entidades.Calificacion;
import co.edu.uniquindio.uniMarket.entidades.Usuario;

public interface UsuarioServicio {

    int registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuario) throws Exception;

    int eliminarUsuario(Integer codigoUsuario) throws Exception;

    UsuarioGetDTO obtenerUsuario(Integer codigoUsuario) throws Exception;

    Usuario obtener(Integer codigoUsuario) throws Exception;

    void validarExiste(int codigoUsuario) throws Exception;

    boolean cambiarContraseña(String email,String passwordNueva) throws Exception;

    CalificacionGetDTO asignarCalificacion(CalificacionDTO calificacionDTO) throws Exception;

    Double promedioPelicula (ProductoDTO productoDTO);




}
