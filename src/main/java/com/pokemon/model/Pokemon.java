package com.pokemon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pokemon_pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tipo_pokemon", nullable = false)
    @JsonProperty("tipo_pokemon")
    private TipoPokemon tipoPokemon;

    @Column(name = "fecha_descubrimiento", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("fecha_descubrimiento")
    private LocalDate fechaDescubrimiento;

    @Column(nullable = false)
    private Integer generacion;

    @Column(nullable = false)
    private String uuid;

    public Pokemon() {}

    public Pokemon(Long id, String nombre, String descripcion, TipoPokemon tipoPokemon, LocalDate fechaDescubrimiento, Integer generacion, String uuid) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoPokemon = tipoPokemon;
        this.fechaDescubrimiento = fechaDescubrimiento;
        this.generacion = generacion;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoPokemon getTipoPokemon() {
        return tipoPokemon;
    }

    public void setTipoPokemon(TipoPokemon tipoPokemon) {
        this.tipoPokemon = tipoPokemon;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
