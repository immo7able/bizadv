package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;
    @Value("${upload.path}")
    private String uploadPath;
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");
    public Map<String, String> validateAnimeData(String name,
                                                 int seriescount,
                                                 String status,
                                                 String studio,
                                                 String typeofanime,
                                                 String sourceofanime,
                                                 MultipartFile avatar,
                                                 MultipartFile background) throws IOException {
        Map<String, String> errors = new HashMap<>();
        if(name.isEmpty()){
            errors.put("name","Имя пустое");
        }
        if(seriescount<=0){
            errors.put("series_count","Недопустимое число");
        }
        if(!status.equals("announcement")&&!status.equals("ongoing")&&!status.equals("completed")){
            errors.put("status",status);
        }
        if (studio.isEmpty()) {
            errors.put("studio", "Студия пуста");
        }
        if (!typeofanime.equals("series")&&!typeofanime.equals("movie")&&!typeofanime.equals("ova")&&!typeofanime.equals("ona")&&!typeofanime.equals("special")){
            errors.put("typeofanime", typeofanime);
        }
        if (sourceofanime.isEmpty()) {
            errors.put("sourceofanime", "Источник пуст");
        }
        if ((avatar == null) || avatar.getOriginalFilename().isEmpty()) {
            errors.put("avatar", "Аватар пуст");
        }
        if ((background == null) || background.getOriginalFilename().isEmpty()) {
            errors.put("background", "Бэкграунд пуст");
        }
        if(animeRepository.existsByName(name)){
            errors.put("name", "Данное имя уже занято");
        }
        if (errors.isEmpty()) {
            Anime anime = new Anime();
            anime.setName(name);
            anime.setSeriesCount(seriescount);
            anime.setStatus(status);
            anime.setStudio(studio);
            anime.setTypeOfAnime(typeofanime);
            anime.setSourceOfAnime(sourceofanime);
            anime.setOutdate(new Date());
            anime.setViews(0);
            anime.setRating(0);
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String UUIDAvatar = UUID.randomUUID().toString();
            String UUIDBackround = UUID.randomUUID().toString();
            String resultAvatarName = UUIDAvatar+"."+avatar.getOriginalFilename();
            String resultBackgroundName = UUIDBackround+"."+background.getOriginalFilename();
            avatar.transferTo(new File(uploadPath+"/"+resultAvatarName));
            background.transferTo(new File(uploadPath+"/"+resultBackgroundName));
            anime.setAvatar(resultAvatarName);
            anime.setBackground(resultBackgroundName);
            animeRepository.save(anime);
        }
        return errors;
    }
    private boolean isAllowedCharacters(String input) {
        return ALLOWED_CHARACTERS_PATTERN.matcher(input).matches();
    }
    public boolean isAnimeExists(int anime_id){
        return animeRepository.existsById(anime_id);
    }
    public Anime getOneAnime(int anime_id){
        return animeRepository.getReferenceById(anime_id);
    }
    public List<Anime> getAnime(){
        return animeRepository.findAll();
    }
}
