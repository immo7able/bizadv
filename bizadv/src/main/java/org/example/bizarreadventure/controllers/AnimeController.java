package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.AnimeSeries;
import org.example.bizarreadventure.entity.CommentDTO;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AnimeController {
    @Autowired
    private AnimeService animeService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/anime")
    public String getAnimePage(Model model){
        List<Anime> animeList = new ArrayList<>();
        animeList = animeService.getAnime();
        model.addAttribute("animeList", animeList);
        return "anime";
    }
    @GetMapping("/anime/{id}")
    public String getAnimeDetails(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
        Anime anime = new Anime();
        List<AnimeSeries> animeSeries = new ArrayList<>();
        User user = (User) httpSession.getAttribute("user");
        if (animeService.isAnimeExists(id)) {
            animeSeries = animeService.getAllSeries(id);
            if (!animeSeries.isEmpty()) {
                model.addAttribute("animeseries", animeSeries);
            }
            anime = animeService.getOneAnime(id);
            model.addAttribute("anime", anime);
            model.addAttribute("user", user);
            List<CommentDTO> comments = commentService.getCommentsByAnimeId(id);
            model.addAttribute("comments", comments);
            return "single";
        } else {
            return "redirect:/index";
        }
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
            List<CommentDTO> comments = commentService.getCommentsByAnimeId(id);
            model.addAttribute("comments", comments);
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
