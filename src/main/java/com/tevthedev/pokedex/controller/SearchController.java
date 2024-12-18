package com.tevthedev.pokedex.controller;

import com.tevthedev.pokedex.helpers.TypeIconMappingService;
import com.tevthedev.pokedex.models.Pokemon;
import com.tevthedev.pokedex.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final PokemonService pokemonService;
    private final TypeIconMappingService typeIconMappingService;

    public SearchController(PokemonService pokemonService, TypeIconMappingService typeIconMappingService) {
        this.pokemonService = pokemonService;
        this.typeIconMappingService = typeIconMappingService;
    }

    @GetMapping
    public String searchPokemon(@RequestParam(name = "search") String searchString, Model model) {
        List<Pokemon> pokemonToDisplay = new ArrayList<>();
        if (searchString != null) {
            Pokemon pokemon = pokemonService.findByName(searchString);
            if (pokemon != null) {
                pokemonToDisplay.add(pokemon);
            }
        }
        model.addAttribute("typeIcons", typeIconMappingService.getIcons());
        model.addAttribute("allPokemon", pokemonToDisplay);
        return "index";
    }

}
