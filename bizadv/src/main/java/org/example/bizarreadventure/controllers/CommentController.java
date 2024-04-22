package org.example.bizarreadventure.controllers;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.Comment;
import org.example.bizarreadventure.entity.CommentDTO;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.service.UserService;
import org.example.bizarreadventure.service.AnimeService;
import org.example.bizarreadventure.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/anime/comments")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;
    @Autowired
    private AnimeService animeService;
    @Autowired
    private UserService userService;


    @PostMapping("/{animeId}/{userId}")
    public String saveComment(@PathVariable("userId") int userId,
                              @PathVariable("animeId") int animeId,
                              @RequestParam("comment") String commentText,
                              Model model) {
        try {
            User user = userService.getUserById(userId);
            Anime anime = animeService.getOneAnime(animeId);
            Map<String, String> errors = commentService.validateCommentData(commentText);
            if (!errors.isEmpty()) {
                model.addAttribute("validationErrors", errors);
                return "redirect:/anime/" + animeId + "?error=validationError";
            }

            Comment createdComment = commentService.addComment(user, anime, commentText);
            if (createdComment != null) {
                return "redirect:/anime/" + animeId;
            } else {
                model.addAttribute("error", "Не удалось добавить комментарий");
                return "redirect:/anime/" + animeId + "?error=commentNotAdded";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Произошла непредвиденная ошибка");
            return "redirect:/anime/" + animeId + "?error=unexpectedError";
        }
    }

}

