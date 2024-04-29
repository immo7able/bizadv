package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.*;
import org.example.bizarreadventure.service.*;
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
public class AnimeController {
    @Autowired
    private AnimeService animeService;
    @Autowired
    private UserService userService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private UserRatingService userRatingService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FavoriteService favoriteService;
    @GetMapping("/anime")
    public String getAnimePage(@RequestParam(required = false) String search, Model model){
        List<Anime> animeList = new ArrayList<>();
        if(search!=null&&!search.isEmpty())
            animeList = animeService.getAnimeByName(search);
        else
            animeList=animeService.getAnime();
        model.addAttribute("animeList", animeList);
        return "anime";
    }
    @GetMapping("/")
    public String getMainPage(@RequestParam(required = false) String search, Model model){
        List<Anime> animeList = new ArrayList<>();
        if(search!=null&&!search.isEmpty())
            animeList = animeService.getAnimeByName(search);
        else
            animeList=animeService.getAnime();
        model.addAttribute("animeList", animeList);
        return "index";
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
            model.addAttribute("averageRating", anime.getRating());
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
            return "redirect:/";
        }
    }

    @PostMapping("")
    public String rateAnime(@RequestParam("animeId") int animeId,
                            @RequestParam("userId") Long userId,
                            @RequestParam("rating") int rating,
                            RedirectAttributes redirectAttrs) {
        Anime anime = animeService.getOneAnime(animeId);
        if (anime == null) {
            redirectAttrs.addFlashAttribute("error", "Аниме не найдено.");
            return "redirect:/anime";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            redirectAttrs.addFlashAttribute("error", "Пользователь не найден.");
            return "redirect:/anime/" + animeId;
        }

        boolean success = userRatingService.rateAnime(user, anime, rating);
        if (success) {
            redirectAttrs.addFlashAttribute("message", "Рейтинг успешно обновлен.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Не удалось обновить рейтинг.");
        }

        return "redirect:/anime/" + animeId;
    }

    @PostMapping("/anime/addToFavorites")
    public String addToFavorites(@RequestParam("animeId") int animeId, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Anime anime = animeService.getOneAnime(animeId);
            if (anime != null) {
                favoriteService.addToFavorites(user, anime);
                return "redirect:/anime/" + animeId;
            } else {
                redirectAttributes.addFlashAttribute("error", "Аниме с ID " + animeId + " не найдено.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Для добавления аниме в избранное необходимо войти в систему.");
        }
        return "redirect:/login";
    }
    @PostMapping("/anime/subscribe")
    public String subscribe(@RequestParam("anime_id") int anime_id, @RequestParam("user_id") int user_id, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Anime anime = animeService.getOneAnime(anime_id);
            if (anime != null) {
                animeService.subscribeToAnime(user, anime);
                return "redirect:/anime/" + anime_id;
            } else {
                redirectAttributes.addFlashAttribute("error", "Аниме с ID " + anime_id + " не найдено.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Для подписки на аниме необходимо войти в систему.");
        }
        return "redirect:/login";
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
            animeService.notifySubscribers(id);
            model.addAttribute("message", "Серия добавлена");
            return "single";
        }
        model.addAttribute("errors", errors);
        return "single";
    }

}