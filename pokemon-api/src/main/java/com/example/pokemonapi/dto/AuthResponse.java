package com.example.pokemonapi.dto;

public record AuthResponse(String accessToken, String tokenType, long expiresIn, String role, String username) {}
