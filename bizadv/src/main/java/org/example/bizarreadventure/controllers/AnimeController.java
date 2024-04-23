package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.CommentService;
import org.example.bizarreadventure.service.GenreService;
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
    private GenreService genreService;
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
    public String showAnimePage(@PathVariable(value = "id") int animeId, Model model, HttpSession httpSession) {
        Anime anime = animeService.getOneAnime(animeId);
        List<AnimeSeries> animeSeries = new ArrayList<>();
        User user = (User) httpSession.getAttribute("user");

        if (animeService.isAnimeExists(animeId)) {
            animeSeries = animeService.getAllSeries(animeId);
            if (!animeSeries.isEmpty()) {
                model.addAttribute("animeseries", animeSeries);
            }

            model.addAttribute("anime", anime);
            if (user != null) {
                model.addAttribute("user", user);
            }
            List<CommentDTO> comments = commentService.getCommentsByAnimeId(animeId);
            model.addAttribute("comments", comments);
            List<Genre> genres = genreService.getAllGenres();
            if (genres.isEmpty()) {
                System.out.println("No genres found");
            } else {
                model.addAttribute("genres", genres);
            }
            List<AnimeGenreDTO> animeGenres = animeService.getGenresForAnime(animeId);
            model.addAttribute("animeGenres", animeGenres);
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