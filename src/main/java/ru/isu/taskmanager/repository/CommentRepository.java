package ru.isu.taskmanager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isu.taskmanager.model.Comment;

@Repository
public interface CommentRepository
        extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.taskId = :task_id")
    List<Comment> findCommentsByTask(@Param("task_id") Integer task_id);
}
