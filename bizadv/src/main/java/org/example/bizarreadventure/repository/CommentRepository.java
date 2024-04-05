package org.example.bizarreadventure.repository;


import org.example.bizarreadventure.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {
    void storeComment(Comment comment);

}
