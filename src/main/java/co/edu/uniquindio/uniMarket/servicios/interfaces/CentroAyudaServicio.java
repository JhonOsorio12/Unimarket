package co.edu.uniquindio.uniMarket.servicios.interfaces;

import co.edu.uniquindio.uniMarket.DTO.CentroAyudaDTO;
import co.edu.uniquindio.uniMarket.DTO.CentroAyudaGetDTO;

import java.util.List;

public interface CentroAyudaServicio {

    int crearMensaje(CentroAyudaDTO centroAyudaDTO);

    List<CentroAyudaGetDTO> listarMensajesCentroAyuda(Integer codigoCentroAyuda);

}
