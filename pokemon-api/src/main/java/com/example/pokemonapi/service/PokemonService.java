package com.example.pokemonapi.service;

import com.example.pokemonapi.dto.PokemonBasicDTO;
import com.example.pokemonapi.dto.PokemonDetailDTO;
import com.example.pokemonapi.dto.PokemonRequest;
import com.example.pokemonapi.exception.NotFoundException;
import com.example.pokemonapi.model.Pokemon;
import com.example.pokemonapi.repository.PokemonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<PokemonBasicDTO> listBasic() {
        return repository.findAll().stream()
                .map(p -> new PokemonBasicDTO(p.getId(), p.getNombre()))
                .collect(Collectors.toList());
    }

    public List<PokemonDetailDTO> listDetail() {
        return repository.findAll().stream()
                .map(p -> new PokemonDetailDTO(
                        p.getId(), p.getNombre(), p.getTipo(), p.getNivel(), p.getPs(),
                        p.getAtaque(), p.getDefensa(), p.getVelocidad(), p.getImagenUrl()))
                .collect(Collectors.toList());
    }

    public PokemonDetailDTO getById(Long id) {
        Pokemon p = repository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon not found"));
        return new PokemonDetailDTO(p.getId(), p.getNombre(), p.getTipo(), p.getNivel(), p.getPs(),
                p.getAtaque(), p.getDefensa(), p.getVelocidad(), p.getImagenUrl());
    }

    public PokemonDetailDTO create(PokemonRequest request) {
        Pokemon p = new Pokemon();
        p.setNombre(request.nombre());
        p.setTipo(request.tipo());
        p.setNivel(request.nivel());
        p.setPs(request.ps());
        p.setAtaque(request.ataque());
        p.setDefensa(request.defensa());
        p.setVelocidad(request.velocidad());
        p.setImagenUrl(request.imagenUrl());
        Pokemon saved = repository.save(p);
        return new PokemonDetailDTO(saved.getId(), saved.getNombre(), saved.getTipo(), saved.getNivel(), saved.getPs(),
                saved.getAtaque(), saved.getDefensa(), saved.getVelocidad(), saved.getImagenUrl());
    }

    public PokemonDetailDTO update(Long id, PokemonRequest request) {
        Pokemon p = repository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon not found"));
        p.setNombre(request.nombre());
        p.setTipo(request.tipo());
        p.setNivel(request.nivel());
        p.setPs(request.ps());
        p.setAtaque(request.ataque());
        p.setDefensa(request.defensa());
        p.setVelocidad(request.velocidad());
        p.setImagenUrl(request.imagenUrl());
        Pokemon saved = repository.save(p);
        return new PokemonDetailDTO(saved.getId(), saved.getNombre(), saved.getTipo(), saved.getNivel(), saved.getPs(),
                saved.getAtaque(), saved.getDefensa(), saved.getVelocidad(), saved.getImagenUrl());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Pokemon not found");
        }
        repository.deleteById(id);
    }
}
