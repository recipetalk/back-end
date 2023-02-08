package com.solution.recipetalk.controller.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.entity.UserProvider;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.comment.CommentCreateDTO;
import com.solution.recipetalk.service.comment.RegisterCommentService;
import com.solution.recipetalk.util.ContextHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    String jwtToken;

    MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype()
    );

    @BeforeAll
    void setUserDetailAndLoginAndBoard() throws Exception {
        mvc.perform(post("/auth/login")
                        .content("{\"username\": \"khj745700\",\"password\": \"testtest\"}")
                        .contentType(contentType))
                .andExpect(status().isOk())
                .andDo(result -> jwtToken = Objects.requireNonNull(result.getResponse().getHeader("Authorization")));

        UserDetail userDetail = UserDetail.builder().id(1L).nickname("test1").phoneNum("01031798788").profileImageURI("testURI").build();
        userDetailRepository.save(userDetail);

        Board sample = Board.builder()
                .writer(userDetail)
                .title("sample")
                .view_count(1L)
                .build();
        boardRepository.save(sample);
    }

    @Test
    void commentRegisterTest() throws Exception {
        CommentCreateDTO request = CommentCreateDTO.builder()
                .parentCommentId(null)
                .description("sample comment")
                .build();

        String content = objectMapper.writeValueAsString(request);

        mvc.perform(post("/api/board/1L/comment").header("Authorization", jwtToken)
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("sample comment")));
    }
}