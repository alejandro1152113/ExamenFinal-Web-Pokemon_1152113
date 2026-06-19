package com.pokemon.controller;

import com.pokemon.dto.LoginRequest;
import com.pokemon.dto.LoginResponse;
import com.pokemon.model.Entrenador;
import com.pokemon.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

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
                    .body("{\"error\": \"true\", \"message\": \"Entrenador no encontrado\"}");
        }
    }
}
