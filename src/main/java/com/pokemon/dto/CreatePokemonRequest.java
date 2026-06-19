package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class CreatePokemonRequest {
    private String nombre;
    private String descripcion;

    @JsonProperty("fecha_descubrimiento")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDescubrimiento;

    private Integer generacion;

    @JsonProperty("tipo_pokemon")
    private String tipoPokemon;

    public CreatePokemonRequest() {}

    public CreatePokemonRequest(String nombre, String descripcion, LocalDate fechaDescubrimiento, Integer generacion, String tipoPokemon) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDescubrimiento = fechaDescubrimiento;
        this.generacion = generacion;
        this.tipoPokemon = tipoPokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaDescubrimiento() {
        return fechaDescubrimiento;
    }

    public void setFechaDescubrimiento(LocalDate fechaDescubrimiento) {
        this.fechaDescubrimiento = fechaDescubrimiento;
    }

    public Integer getGeneracion() {
        return generacion;
    }

    public void setGeneracion(Integer generacion) {
        this.generacion = generacion;
    }

    public String getTipoPokemon() {
        return tipoPokemon;
    }

    public void setTipoPokemon(String tipoPokemon) {
        this.tipoPokemon = tipoPokemon;
    }
}
