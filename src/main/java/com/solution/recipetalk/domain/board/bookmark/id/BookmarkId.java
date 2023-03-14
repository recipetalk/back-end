package com.solution.recipetalk.domain.board.bookmark.id;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkId implements Serializable {
    private UserDetail user;
    private Board board;
}
