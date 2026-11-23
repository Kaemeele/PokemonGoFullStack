package com.example.pokemonapi.controller;

import com.example.pokemonapi.dto.PokemonDetailDTO;
import com.example.pokemonapi.dto.PokemonRequest;
import com.example.pokemonapi.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/pokemons")
public class PokemonV2Controller {
    private final PokemonService service;

    public PokemonV2Controller(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PokemonDetailDTO> list() {
        return service.listDetail();
    }

    @GetMapping("/{id}")
    public PokemonDetailDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PokemonDetailDTO update(@PathVariable Long id, @Valid @RequestBody PokemonRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
