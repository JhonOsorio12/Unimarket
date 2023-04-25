package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.CentroAyudaDTO;
import co.edu.uniquindio.uniMarket.DTO.CentroAyudaGetDTO;
import co.edu.uniquindio.uniMarket.entidades.CentroAyuda;
import co.edu.uniquindio.uniMarket.entidades.Compra;

import java.util.List;

public interface CentroAyudaServicio {

    int crearMensaje(CentroAyudaDTO centroAyudaDTO) throws Exception;

    List<CentroAyudaGetDTO> listarMensajesCentroAyuda(Integer codigoCentroAyuda) throws Exception;

    CentroAyudaGetDTO obtenerCentroAyuda(Integer codigoCentroAyuda) throws Exception;

}
