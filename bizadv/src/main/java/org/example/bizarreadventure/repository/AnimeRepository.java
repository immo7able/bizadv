package org.example.bizarreadventure.repository;
import org.example.bizarreadventure.entity.Anime;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository {
    void storeAnime(Anime anime);
}
