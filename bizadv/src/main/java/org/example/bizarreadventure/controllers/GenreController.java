package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.service.AnimeGenreService;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GenreController {
    @Autowired
    private AnimeGenreService animeGenreService;
    @Autowired
    private AnimeService animeService;
    @Autowired
    private GenreService genreService;
    @GetMapping("/genre/{id}")
    public String getAnimePage(@PathVariable(value = "id") int genreId, Model model){
        Genre genre = genreService.getGenre(genreId);
        List<Anime> anime= new ArrayList<>();
        if (genreService.isGenreExists(genreId)) {
            anime = animeGenreService.getAnimeByGenre(genreId);
            genre = genreService.getGenre(genreId);
            if (!anime.isEmpty()) {
                model.addAttribute("animeList", anime);
            }
            if (genre!=null) {
                model.addAttribute("genre", genre);
            }
            return "anime";
        } else {
            return "redirect:/";
        }
    }
}
