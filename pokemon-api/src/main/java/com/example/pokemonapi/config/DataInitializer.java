package com.example.pokemonapi.config;

import com.example.pokemonapi.model.Pokemon;
import com.example.pokemonapi.model.User;
import com.example.pokemonapi.model.User.Role;
import com.example.pokemonapi.repository.PokemonRepository;
import com.example.pokemonapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(UserRepository userRepo, PokemonRepository pokemonRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepo.save(admin);
            }
            if (userRepo.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                userRepo.save(user);
            }
            if (pokemonRepo.count() == 0) {
                Pokemon p1 = new Pokemon();
                p1.setNombre("Pikachu");
                p1.setTipo("Electric");
                p1.setNivel(5);
                p1.setPs(35);
                p1.setAtaque(55);
                p1.setDefensa(40);
                p1.setVelocidad(90);
                p1.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png");
                Pokemon p2 = new Pokemon();
                p2.setNombre("Charmander");
                p2.setTipo("Fire");
                p2.setNivel(5);
                p2.setPs(39);
                p2.setAtaque(52);
                p2.setDefensa(43);
                p2.setVelocidad(65);
                p2.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png");
                Pokemon p3 = new Pokemon();
                p3.setNombre("Squirtle");
                p3.setTipo("Water");
                p3.setNivel(5);
                p3.setPs(44);
                p3.setAtaque(48);
                p3.setDefensa(65);
                p3.setVelocidad(43);
                p3.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png");
                pokemonRepo.save(p1);
                pokemonRepo.save(p2);
                pokemonRepo.save(p3);
            }
        };
    }
}
