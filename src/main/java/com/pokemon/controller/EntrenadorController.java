package com.pokemon.controller;

import com.pokemon.dto.LoginRequest;
import com.pokemon.dto.LoginResponse;
import com.pokemon.model.Entrenador;
import com.pokemon.model.Pokemon;
import com.pokemon.repository.EntrenadorRepository;
import com.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Entrenador> entrenadorOpt = entrenadorRepository.findByEmailIgnoreCase(loginRequest.getEmail());
        if (entrenadorOpt.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(entrenadorOpt.get().getUuid()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{uuid}/pokemons")
    public ResponseEntity<?> getPokemonsByEntrenador(@PathVariable String uuid) {
        Optional<Entrenador> entrenadorOpt = entrenadorRepository.findByUuid(uuid);
        if (entrenadorOpt.isPresent()) {
            return ResponseEntity.ok(entrenadorOpt.get().getPokemons());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"true\", \"message\": \"Entrenador no encontrado\"}");
        }
    }

    @PostMapping("/{uuid}/pokemons/{pokemonUuid}")
    public ResponseEntity<?> associatePokemon(
            @PathVariable String uuid,
            @PathVariable String pokemonUuid) {

        Optional<Entrenador> entrenadorOpt = entrenadorRepository.findByUuid(uuid);
        if (entrenadorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"true\", \"message\": \"Entrenador no encontrado\"}");
        }

        Optional<Pokemon> pokemonOpt = pokemonRepository.findByUuid(pokemonUuid);
        if (pokemonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"true\", \"message\": \"Pokemon no encontrado\"}");
        }

        Entrenador entrenador = entrenadorOpt.get();
        Pokemon pokemon = pokemonOpt.get();

        // Check if already registered
        boolean alreadyAssociated = entrenador.getPokemons().stream()
                .anyMatch(p -> p.getUuid().equals(pokemon.getUuid()));

        if (alreadyAssociated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"true\", \"message\": \"pokemon ya esta registrado al entrenador\"}");
        }

        // Associate
        entrenador.getPokemons().add(pokemon);
        entrenadorRepository.save(entrenador);

        return ResponseEntity.ok(entrenador.getPokemons());
    }
}
