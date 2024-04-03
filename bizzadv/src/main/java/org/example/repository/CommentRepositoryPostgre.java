package org.example.repository;

import org.example.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryPostgre implements CommentRepository{

    @Override
    public void storeComment(Comment comment) {
    }
}
