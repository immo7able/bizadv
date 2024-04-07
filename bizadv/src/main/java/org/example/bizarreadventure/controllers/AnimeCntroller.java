package org.example.bizarreadventure.controllers;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnimeCntroller {
    @Autowired
    private AnimeService animeService;
    @GetMapping("/anime")
    public String getAnimePage(Model model){
        List<Anime> animeList = new ArrayList<>();
        animeList = animeService.getAnime();
        model.addAttribute("animeList", animeList);
        return "anime";
    }
    @GetMapping("/anime/{id}")
    public String getAnimePageDetails(@PathVariable(value = "id") int id, Model model){
        Anime anime = new Anime();
        if(animeService.isAnimeExists(id)){
            anime = animeService.getOneAnime(id);
            model.addAttribute("anime", anime);
            return "single";
        }
        else return "redirect:/index";
    }
}
