package org.example.bizarreadventure.controllers;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.UserRatingService;
import org.example.bizarreadventure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/anime/rating")
public class UserRatingController {
    private final UserRatingService userRatingService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnimeService animeService;

    @Autowired
    public UserRatingController(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    @PostMapping("")
    public String rateAnime(@RequestParam("animeId") int animeId,
                            @RequestParam("userId") int userId,
                            @RequestParam("rating") int rating) {

        User user = userService.getUserById(userId);
        Anime anime = animeService.getOneAnime(animeId);

        if (user == null || anime == null) {
            return "redirect:/anime/error";
        }

        userRatingService.rateAnime(user, anime, rating);

        return "redirect:/anime/" +  animeId;
    }
}
