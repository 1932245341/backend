package org.example.server.controller.user;

import org.apache.ibatis.annotations.Delete;
import org.example.common.context.BaseContext;
import org.example.common.result.Result;
import org.example.pojo.entity.Comment;
import org.example.server.server.interfa.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.insert(comment);
        return Result.success(comment);
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody Comment comment) {
        if (comment.getUserId() == BaseContext.getCurrentId()) {
            commentService.deleteById(comment);
        }
        return Result.success();
    }

    @PostMapping("/list")
    public Result<List<Comment>> qurey(@RequestBody Comment comment) {
        return Result.success(commentService.qurey(comment));
    }
}
