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
                p1.setNombre("Bulbasaur");
                p1.setTipo("Grass/Poison");
                p1.setNivel(5);
                p1.setPs(45);
                p1.setAtaque(49);
                p1.setDefensa(49);
                p1.setVelocidad(45);
                p1.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
                p1.setNumeroDex(1);
                p1.setLore("A strange seed was planted on its back at birth. The plant slowly grows with this Pokémon.");
                pokemonRepo.save(p1);

                Pokemon p2 = new Pokemon();
                p2.setNombre("Charmander");
                p2.setTipo("Fire");
                p2.setNivel(5);
                p2.setPs(39);
                p2.setAtaque(52);
                p2.setDefensa(43);
                p2.setVelocidad(65);
                p2.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png");
                p2.setNumeroDex(4);
                p2.setLore("It has a preference for hot things. When it rains, steam is said to spout from the tip of its tail.");
                pokemonRepo.save(p2);

                Pokemon p3 = new Pokemon();
                p3.setNombre("Squirtle");
                p3.setTipo("Water");
                p3.setNivel(5);
                p3.setPs(44);
                p3.setAtaque(48);
                p3.setDefensa(65);
                p3.setVelocidad(43);
                p3.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png");
                p3.setNumeroDex(7);
                p3.setLore("Shoots water at prey while in its shell. The water spouts violently.");
                pokemonRepo.save(p3);

                Pokemon p4 = new Pokemon();
                p4.setNombre("Pikachu");
                p4.setTipo("Electric");
                p4.setNivel(5);
                p4.setPs(35);
                p4.setAtaque(55);
                p4.setDefensa(40);
                p4.setVelocidad(90);
                p4.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png");
                p4.setNumeroDex(25);
                p4.setLore("When several of these Pokémon gather, their electricity could build and cause lightning storms.");
                pokemonRepo.save(p4);

                Pokemon p5 = new Pokemon();
                p5.setNombre("Gengar");
                p5.setTipo("Ghost/Poison");
                p5.setNivel(30);
                p5.setPs(60);
                p5.setAtaque(65);
                p5.setDefensa(60);
                p5.setVelocidad(110);
                p5.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/94.png");
                p5.setNumeroDex(94);
                p5.setLore("Under a full moon, this Pokémon likes to mimic the shadows of people and laugh.");
                pokemonRepo.save(p5);

                Pokemon p6 = new Pokemon();
                p6.setNombre("Dragonite");
                p6.setTipo("Dragon/Flying");
                p6.setNivel(55);
                p6.setPs(91);
                p6.setAtaque(134);
                p6.setDefensa(95);
                p6.setVelocidad(80);
                p6.setImagenUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/149.png");
                p6.setNumeroDex(149);
                p6.setLore("This Pokémon is said to be able to circle the globe in just twelve hours.");
                pokemonRepo.save(p6);
            }
        };
    }
}
