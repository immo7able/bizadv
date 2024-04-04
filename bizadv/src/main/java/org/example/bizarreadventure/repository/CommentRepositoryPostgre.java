package org.example.bizarreadventure.repository;

import org.example.bizarreadventure.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryPostgre implements CommentRepository{

    @Override
    public void storeComment(Comment comment) {
    }
}
