package com.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_tipo_pokemon")
public class TipoPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String uuid;

    public TipoPokemon() {}

    public TipoPokemon(Long id, String descripcion, String uuid) {
        this.id = id;
        this.descripcion = descripcion;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
