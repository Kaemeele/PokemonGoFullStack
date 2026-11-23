package com.example.pokemonapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PokemonRequest(
    @NotBlank String nombre,
    @NotBlank String tipo,
    @NotNull @Positive Integer nivel,
    @NotNull @Positive Integer ps,
    @NotNull @Positive Integer ataque,
    @NotNull @Positive Integer defensa,
    @NotNull @Positive Integer velocidad,
    String imagenUrl
) {}