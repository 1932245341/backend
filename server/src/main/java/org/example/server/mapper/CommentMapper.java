package org.example.server.mapper;

import org.example.pojo.entity.Comment;

import java.util.List;

public interface CommentMapper {

   void insert(Comment comment);

   void deleteById(Comment comment);

   List<Comment> qurey(Comment comment);

}
