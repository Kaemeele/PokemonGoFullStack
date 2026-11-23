package com.example.pokemonapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    private String tipo;

    @Min(1)
    @Max(100)
    private Integer nivel;

    private Integer ps;
    private Integer ataque;
    private Integer defensa;
    private Integer velocidad;
    private String imagenUrl;
}
