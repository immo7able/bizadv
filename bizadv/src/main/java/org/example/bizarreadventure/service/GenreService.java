package org.example.bizarreadventure.service;

import org.example.bizarreadventure.entity.Anime;
import org.example.bizarreadventure.entity.AnimeGenreDTO;
import org.example.bizarreadventure.entity.Genre;
import org.example.bizarreadventure.repository.AnimeGenreRepository;
import org.example.bizarreadventure.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AnimeGenreRepository animeGenreRepository;

    @Autowired
    private AnimeGenreService animeGenreService;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genreRepository.save(genre);
    }
    public List<AnimeGenreDTO> getGenresForAnime(int animeId) {
        return animeGenreService.getGenresForAnime(animeId);
    }
    public Genre getGenre(int id){
        Optional<Genre> animeOptional = genreRepository.findById(id);
        return animeOptional.orElse(null);
    }
    public boolean isGenreExists(int id){
        return genreRepository.existsById(id);
    }
}
