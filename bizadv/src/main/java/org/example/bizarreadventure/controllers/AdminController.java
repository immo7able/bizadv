package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.GenreService;
import org.example.bizarreadventure.service.UserService;
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
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private GenreService genreService;


    @Autowired
    private AnimeService animeService;
    @GetMapping("/admin")
    public String getAdminPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals("admin")) {
            model.addAttribute("user", user);
            return "admin";
        } else {
            if(user==null)
                return "redirect:/login";
            else return "redirect:/profile";
        }
    }
    @PostMapping("/admin")
    public String createUser(@RequestParam String name,
                             @RequestParam int seriescount,
                             @RequestParam String status,
                             @RequestParam String studio,
                             @RequestParam String typeofanime,
                             @RequestParam String sourceofanime,
                             @RequestParam MultipartFile avatar,
                             @RequestParam MultipartFile background,
                             Model model) throws IOException {
        Map<String, String> errors = animeService.validateAnimeData(name, seriescount, status, studio, typeofanime, sourceofanime, avatar, background);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "admin";
        }
        model.addAttribute("message", "Аниме добавлено");
        return "admin";
    }

    @PostMapping("/genre")
    public String addGenre(@RequestParam String name,
                           Model model) {
        genreService.addGenre(name);
        model.addAttribute("messagegenre", "Жанр добавлен");
        return "admin";
    }
}
