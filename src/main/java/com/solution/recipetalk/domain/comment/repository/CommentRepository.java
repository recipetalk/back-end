package com.solution.recipetalk.domain.comment.repository;

import com.solution.recipetalk.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
