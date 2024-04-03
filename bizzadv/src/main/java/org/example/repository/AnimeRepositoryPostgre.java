package org.example.repository;

import org.example.entity.Anime;
import org.springframework.stereotype.Repository;

@Repository
public class AnimeRepositoryPostgre implements AnimeRepository{
    @Override
    public void storeAnime(Anime anime) {

    }
}
