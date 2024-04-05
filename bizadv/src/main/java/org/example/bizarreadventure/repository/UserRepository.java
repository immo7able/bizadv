package org.example.bizarreadventure.repository;


import org.example.bizarreadventure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    User findByLogin(String login);
}
