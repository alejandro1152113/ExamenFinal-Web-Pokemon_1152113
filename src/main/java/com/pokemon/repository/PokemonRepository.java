package com.pokemon.repository;

import com.pokemon.model.Pokemon;
import com.pokemon.model.TipoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByTipoPokemon(TipoPokemon tipoPokemon);
    Optional<Pokemon> findByUuid(String uuid);
}
