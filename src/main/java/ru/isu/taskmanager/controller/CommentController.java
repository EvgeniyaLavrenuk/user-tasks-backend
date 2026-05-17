package ru.isu.taskmanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isu.taskmanager.model.Comment;
import ru.isu.taskmanager.repository.CommentRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments/taskId={taskId}")
    public ResponseEntity<List<Comment>> getByTask(@PathVariable Integer taskId) {
        List<Comment> comments = commentRepository.findCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/updateComment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @RequestBody Comment updated) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        comment.setContent(updated.getContent());
        commentRepository.save(comment);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
