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
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private Integer unidades;

    private float precio;

    @NotNull
    private Integer codigoVendedor;

    @NotNull
    private Map<String, String> imagenes;

    private List<Categoria> categorias;

}
