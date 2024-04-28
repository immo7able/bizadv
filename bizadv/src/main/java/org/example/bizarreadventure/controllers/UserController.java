package org.example.bizarreadventure.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.entity.UserList;
import org.example.bizarreadventure.repository.UserRepository;
import org.example.bizarreadventure.service.FavoriteService;
import org.example.bizarreadventure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;
    @PostMapping("/register")
    public String createUser(@RequestParam String login,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String confirm_password,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        Map<String, String> errors = userService.validateUserData(login, email, password, confirm_password);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "register";
        }
        redirectAttributes.addFlashAttribute("message","Вы успешно зарегистрировались, можете войти.");
        return "redirect:/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        if (userService.authenticate(login, password)) {
            session.setAttribute("user", userService.getUser(login));
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            List<UserList> favorites = favoriteService.findFavoritesByUserId(user);
            model.addAttribute("favorites", favorites);
            userService.updateUserRanking(user, favorites);

            return "profile";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
