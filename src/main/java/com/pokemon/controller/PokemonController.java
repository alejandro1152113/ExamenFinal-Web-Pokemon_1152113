package com.pokemon.controller;

import com.pokemon.dto.CreatePokemonRequest;
import com.pokemon.model.Pokemon;
import com.pokemon.model.TipoPokemon;
import com.pokemon.repository.PokemonRepository;
import com.pokemon.repository.TipoPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TipoPokemonRepository tipoPokemonRepository;

    @GetMapping("/{tipo}")
    public ResponseEntity<List<Pokemon>> getPokemonsByType(@PathVariable String tipo) {
        Optional<TipoPokemon> tipoOpt = Optional.empty();

        // 1. Try by ID
        try {
            Long id = Long.parseLong(tipo);
            tipoOpt = tipoPokemonRepository.findById(id);
        } catch (NumberFormatException e) {
            // Not a number, ignore
        }

        // 2. Try by UUID if not found
        if (tipoOpt.isEmpty()) {
            tipoOpt = tipoPokemonRepository.findByUuid(tipo);
        }

        // 3. Try by description (ignore case) if not found
        if (tipoOpt.isEmpty()) {
            tipoOpt = tipoPokemonRepository.findByDescripcionIgnoreCase(tipo);
        }

        if (tipoOpt.isPresent()) {
            List<Pokemon> pokemons = pokemonRepository.findByTipoPokemon(tipoOpt.get());
            return ResponseEntity.ok(pokemons);
        } else {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPokemon(@RequestBody CreatePokemonRequest request) {
        Optional<TipoPokemon> tipoOpt = Optional.empty();

        // Lookup TipoPokemon by ID, UUID or Description
        if (request.getTipoPokemon() != null) {
            try {
                Long id = Long.parseLong(request.getTipoPokemon());
                tipoOpt = tipoPokemonRepository.findById(id);
            } catch (NumberFormatException e) {
                // Not a number, ignore
            }
            if (tipoOpt.isEmpty()) {
                tipoOpt = tipoPokemonRepository.findByUuid(request.getTipoPokemon());
            }
            if (tipoOpt.isEmpty()) {
                tipoOpt = tipoPokemonRepository.findByDescripcionIgnoreCase(request.getTipoPokemon());
            }
        }

        if (tipoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"true\", \"message\": \"Tipo de Pokemon no encontrado\"}");
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setNombre(request.getNombre());
        pokemon.setDescripcion(request.getDescripcion());
        pokemon.setFechaDescubrimiento(request.getFechaDescubrimiento());
        pokemon.setGeneracion(request.getGeneracion());
        pokemon.setTipoPokemon(tipoOpt.get());
        pokemon.setUuid(UUID.randomUUID().toString());

        Pokemon saved = pokemonRepository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
