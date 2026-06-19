package com.pokemon.controller;

import com.pokemon.dto.LoginRequest;
import com.pokemon.dto.LoginResponse;
import com.pokemon.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return entrenadorRepository.findByEmailIgnoreCase(loginRequest.getEmail())
                .map(entrenador -> ResponseEntity.ok(new LoginResponse(entrenador.getUuid())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
