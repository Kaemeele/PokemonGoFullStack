package com.example.pokemonapi.service;

import com.example.pokemonapi.dto.PokemonBasicDTO;
import com.example.pokemonapi.dto.PokemonDetailDTO;
import com.example.pokemonapi.dto.PokemonRequest;
import com.example.pokemonapi.exception.NotFoundException;
import com.example.pokemonapi.model.Pokemon;
import com.example.pokemonapi.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonRepository repository;

    @InjectMocks
    private PokemonService service;

    @Test
    void listBasicPokemons() {
        Pokemon p1 = new Pokemon();
        p1.setId(1L);
        p1.setNombre("Pikachu");
        Pokemon p2 = new Pokemon();
        p2.setId(2L);
        p2.setNombre("Charmander");
        when(repository.findAll()).thenReturn(List.of(p1, p2));

        List<PokemonBasicDTO> result = service.listBasic();

        assertEquals(2, result.size());
        assertEquals("Pikachu", result.get(0).nombre());
        assertEquals("Charmander", result.get(1).nombre());
        verify(repository).findAll();
    }

    @Test
    void listDetailPokemons() {
        Pokemon p = new Pokemon();
        p.setId(1L);
        p.setNombre("Bulbasaur");
        p.setTipo("Grass");
        p.setNivel(5);
        p.setPs(45);
        p.setAtaque(49);
        p.setDefensa(49);
        p.setVelocidad(45);
        p.setImagenUrl("url");
        when(repository.findAll()).thenReturn(List.of(p));

        List<PokemonDetailDTO> result = service.listDetail();

        assertEquals(1, result.size());
        PokemonDetailDTO dto = result.get(0);
        assertEquals("Bulbasaur", dto.nombre());
        assertEquals("Grass", dto.tipo());
        verify(repository).findAll();
    }

    @Test
    void getPokemonByIdExists() {
        Pokemon p = new Pokemon();
        p.setId(1L);
        p.setNombre("Pikachu");
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        PokemonDetailDTO result = service.getById(1L);

        assertNotNull(result);
        assertEquals("Pikachu", result.nombre());
        verify(repository).findById(1L);
    }

    @Test
    void getPokemonByIdNotExists() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(99L));

        verify(repository).findById(99L);
    }

    @Test
    void createPokemon() {
        PokemonRequest req = new PokemonRequest(
                "Pikachu", "Electric", 5, 35, 55, 40, 90, "url");
        Pokemon saved = new Pokemon();
        saved.setId(1L);
        saved.setNombre("Pikachu");
        when(repository.save(any(Pokemon.class))).thenReturn(saved);

        PokemonDetailDTO result = service.create(req);

        assertNotNull(result);
        assertEquals("Pikachu", result.nombre());
        verify(repository).save(any(Pokemon.class));
    }

    @Test
    void updatePokemonExists() {
        Pokemon existing = new Pokemon();
        existing.setId(2L);
        existing.setNombre("Charmander");
        when(repository.findById(2L)).thenReturn(Optional.of(existing));
        PokemonRequest req = new PokemonRequest(
                "Charmeleon", "Fire", 16, 58, 64, 58, 80, "url");
        when(repository.save(existing)).thenReturn(existing);

        PokemonDetailDTO result = service.update(2L, req);

        assertEquals("Charmeleon", result.nombre());
        verify(repository).findById(2L);
        verify(repository).save(existing);
    }

    @Test
    void deletePokemonExists() {
        Pokemon p = new Pokemon();
        p.setId(3L);
        when(repository.findById(3L)).thenReturn(Optional.of(p));
        doNothing().when(repository).delete(p);

        service.delete(3L);

        verify(repository).findById(3L);
        verify(repository).delete(p);
    }
}
