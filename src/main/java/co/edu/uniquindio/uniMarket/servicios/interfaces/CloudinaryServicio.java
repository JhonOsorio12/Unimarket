package co.edu.uniquindio.uniMarket.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface CloudinaryServicio {

    Map subirImagen(File file, String carpeta) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;

    File convertir(MultipartFile imagen) throws Exception;

}
