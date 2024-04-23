package org.example.bizarreadventure.controllers;

import ch.qos.logback.core.model.Model;
import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.AnimeGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
