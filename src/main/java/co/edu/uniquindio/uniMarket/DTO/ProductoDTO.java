package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    @NotNull
    private String descripcion;
    @NotNull
    private String nombre;

    private float precio;

    @NotNull
    private Integer unidades;

    @NotNull
    private List<ImagenDTO> imagenes;

    private List<Categoria> categorias;

    @NotNull
    private Integer codigoVendedor;

}
