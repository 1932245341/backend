package org.example.server.server.impl;

import org.example.common.context.BaseContext;
import org.example.pojo.entity.Comment;
import org.example.server.mapper.CommentMapper;
import org.example.server.server.interfa.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insert(Comment comment) {
        comment.setUserId(BaseContext.getCurrentId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = LocalDateTime.now().format(formatter);
        comment.setCreateTime(formattedTime);
        commentMapper.insert(comment);
    }

    @Override
    public void deleteById(Comment comment) {
            commentMapper.deleteById(comment);
    }

    @Override
    public List<Comment> qurey(Comment comment) {
        return commentMapper.qurey(comment);
    }
}
