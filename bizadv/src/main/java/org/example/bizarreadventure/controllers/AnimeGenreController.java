package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.AnimeGenreService;
import org.example.bizarreadventure.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/anime/{id}")
public class AnimeGenreController {

    @Autowired
    private AnimeGenreService animeGenreService;

    @Autowired
    private AnimeService animeService;


    @PostMapping("/addGenre")
    public String addGenreToAnime(@PathVariable("id") int animeId, @RequestParam int genreId){
        Anime anime = animeService.getOneAnime(animeId);
        if (anime != null && genreId != 0) {
            animeGenreService.addAnimeGenre(genreId, anime);
            return "redirect:/anime/" + animeId;
        } else {
            return "redirect:/anime/" + animeId + "?error=invalidGenre";
        }
    }

}
