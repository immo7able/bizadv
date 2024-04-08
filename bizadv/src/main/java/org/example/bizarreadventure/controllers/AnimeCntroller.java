package org.example.bizarreadventure.controllers;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.AnimeSeries;
import org.example.bizarreadventure.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<AnimeSeries> animeSeries = new ArrayList<>();
        if(animeService.isAnimeExists(id)){
            animeSeries=animeService.getAllSeries(id);
            if(!animeSeries.isEmpty()){
                model.addAttribute("animeseries", animeSeries);
            }
            anime = animeService.getOneAnime(id);
            model.addAttribute("anime", anime);

            return "single";
        }
        else return "redirect:/index";
    }
    @PostMapping("/anime/{id}")
    public String addAnimeSeries(@PathVariable(value = "id") int id, @RequestParam int number, @RequestParam MultipartFile video, Model model) throws IOException {
        Map<String, String> errors = animeService.addAnimeSerie(id, number, video);
        Anime anime = new Anime();
        List<AnimeSeries> animeSeries = new ArrayList<>();
        if(animeService.isAnimeExists(id)){
            animeSeries=animeService.getAllSeries(id);
            if(!animeSeries.isEmpty()){
                model.addAttribute("animeseries", animeSeries);
            }
            anime = animeService.getOneAnime(id);
            model.addAttribute("anime", anime);
        }
        else return "redirect:/index";
        if (errors.isEmpty()) {
            model.addAttribute("message", "Серия добавлена");
            return "single";
        }
        model.addAttribute("errors", errors);
        return "single";
    }
}
