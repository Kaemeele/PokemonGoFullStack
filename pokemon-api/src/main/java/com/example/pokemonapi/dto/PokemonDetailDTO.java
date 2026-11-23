package com.example.pokemonapi.dto;

public record PokemonDetailDTO(Long id, String nombre, String tipo, Integer nivel, Integer ps, Integer ataque, Integer defensa, Integer velocidad, String imagenUrl, Integer numeroDex, String lore) {}
