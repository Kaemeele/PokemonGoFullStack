package com.example.pokemonapi.controller;

import com.example.pokemonapi.dto.PokemonBasicDTO;
import com.example.pokemonapi.dto.PokemonDetailDTO;
import com.example.pokemonapi.dto.PokemonRequest;
import com.example.pokemonapi.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokemonV1Controller {
    private final PokemonService service;

    public PokemonV1Controller(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PokemonBasicDTO> list() {
        return service.listBasic();
    }

    @PostMapping
    public ResponseEntity<PokemonDetailDTO> create(@Valid @RequestBody PokemonRequest request) {
        PokemonDetailDTO created = service.create(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
