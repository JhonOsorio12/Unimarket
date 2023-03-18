package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.Categoria;

import java.util.List;

public class ProductoDTO {

    private String nombre;

    private String descripcion;

    private Integer unidades;

    private float precio;

    private Integer codigoVendedor;

    private List<String> imagenes;

    private List<Categoria> categorias;

}
