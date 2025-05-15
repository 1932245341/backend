package org.example.server.server.interfa;

import org.example.pojo.entity.Comment;

import java.util.List;

public interface CommentService {

    void insert(Comment comment);
    void deleteById(Comment comment);
    List<Comment> qurey(Comment comment);
}
