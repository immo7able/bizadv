package org.example.bizarreadventure.repository;

import org.springframework.stereotype.Repository;
import org.example.bizarreadventure.entity.Anime;

@Repository
public class AnimeRepositoryPostgre implements AnimeRepository{
    @Override
    public void storeAnime(Anime anime) {

    }
}
