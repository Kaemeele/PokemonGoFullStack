package com.example.pokemonapi.service;

import com.example.pokemonapi.model.Pokemon;
import com.example.pokemonapi.repository.PokemonRepository;
import com.example.pokemonapi.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {
    @Mock
    private PokemonRepository repository;
    @InjectMocks
    private PokemonService service;

    @Test
    void listPokemons() {
        Pokemon p1 = new Pokemon();
        Pokemon p2 = new Pokemon();
        when(repository.findAll()).thenReturn(List.of(p1, p2));
        List<Pokemon> result = service.listAll();
        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void getPokemonByIdExists() {
        Pokemon p = new Pokemon();
        when(repository.findById(1L)).thenReturn(Optional.of(p));
        Pokemon result = service.getById(1L);
        assertNotNull(result);
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
        Pokemon p = new Pokemon();
        when(repository.save(p)).thenReturn(p);
        Pokemon result = service.create(p);
        assertSame(p, result);
        verify(repository).save(p);
    }

    @Test
    void updatePokemonExists() {
        Pokemon existing = new Pokemon();
        existing.setId(2L);
        Pokemon updated = new Pokemon();
        updated.setNombre("Updated");
        when(repository.findById(2L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);
        Pokemon result = service.update(2L, updated);
        assertEquals("Updated", result.getNombre());
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
