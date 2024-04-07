package org.example.bizarreadventure.repository;
import org.example.bizarreadventure.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    boolean existsByName(String name);
}
