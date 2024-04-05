package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.User;
import org.example.bizarreadventure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");

    public Map<String, String> validateUserData(String login, String email, String password, String confirmPassword) {
        Map<String, String> errors = new HashMap<>();
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            errors.put("email", "Неверный формат email");
        }
        if(!password.equals(confirmPassword)){
            errors.put("password","Пароли не совпадают");
        }
        if(password.length()<6){
            errors.put("password","Пароль меньше 6 символов");
        }
        if(login.length()<4){
            errors.put("login","Логин меньше 4 символов");
        }
        if (!isAllowedCharacters(login)) {
            errors.put("login", "Логин содержит недопустимые символы");
        }
        if (!isAllowedCharacters(password)) {
            errors.put("password", "Пароль содержит недопустимые символы");
        }
        if(userRepository.existsByEmail(email)){
            errors.put("email", "Email уже зарегистрирован");
        }
        if(userRepository.existsByLogin(login)){
            errors.put("login", "Login уже зарегистрирован");
        }
        if (errors.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(password);
            user.setReg_date(new Date());
            user.setRole("user");
            userRepository.save(user);
        }
        return errors;
    }
    private boolean isAllowedCharacters(String input) {
        return ALLOWED_CHARACTERS_PATTERN.matcher(input).matches();
    }
    public boolean authenticate(String login, String password) {
        User user = userRepository.findByLogin(login);
        return login != null && !login.isEmpty() && password != null && !password.isEmpty() && user.getPassword().equals(password);
    }
    public User getUser(String login){
        return userRepository.findByLogin(login);
    }
}
