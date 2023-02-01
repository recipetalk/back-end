package com.solution.recipetalk.controller.comment;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.RoleType;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import com.solution.recipetalk.util.ContextHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private RegisterCommentService registerCommentService;

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    void setUserDetailAndLoginAndBoard() {
        UserDetail userDetail = UserDetail.builder().id(1L).nickname("test1").phoneNum("01031798788").provider(UserProvider.GOOGLE).role(RoleType.DEV).profileImageURI("testURI").build();
        userDetailRepository.save(userDetail);

        UserLogin userLogin = UserLogin.builder().userDetail(userDetail).password("test1").pwSalt("11").username("test").build();
        userDetail.setUserLogin(userLogin);
        userDetailRepository.save(userDetail);

        Board sample = Board.builder()
                .writer(userDetail)
                .title("sample")
                .view_count(1L)
                .boardLikes(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();
        boardRepository.save(sample);
    }

    @Test
    void checkCommentDescriptionIsValidTest() throws Exception {
        CommentCreateDTO comment = CommentCreateDTO.builder()
                .writerId(ContextHolder.getUserDetail().getId())
                .parentCommentId(null)
                .description("sample comment")
                .build();

        Long boardId = boardRepository.findBoardByTitle("sample").getId();

        registerCommentService.addComment(boardId, comment);

        mvc.perform(get("/{boardId}/comment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.[0].description",
                        is("sample comment"))
                );
    }
}