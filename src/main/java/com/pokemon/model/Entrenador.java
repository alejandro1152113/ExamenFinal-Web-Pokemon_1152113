package com.pokemon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon_entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_vinculacion", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("fecha_vinculacion")
    private LocalDate fechaVinculacion;

    @ManyToOne
    @JoinColumn(name = "pueblo_id", nullable = false)
    private Pueblo pueblo;

    @Column(nullable = false)
    private String uuid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "pokemon_captura",
        joinColumns = @JoinColumn(name = "entrenador_id"),
        inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemons = new ArrayList<>();

    public Entrenador() {}

    public Entrenador(Long id, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaVinculacion, Pueblo pueblo, String uuid, List<Pokemon> pokemons) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaVinculacion = fechaVinculacion;
        this.pueblo = pueblo;
        this.uuid = uuid;
        this.pokemons = pokemons;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(LocalDate fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public Pueblo getPueblo() {
        return pueblo;
    }

    public void setPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
