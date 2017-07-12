package com.web;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Created by KimYJ on 2017-07-12.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(NAME_ASCENDING)
public class JpaMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email("test@gmail.com")
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                .title("테스트")
                .subTitle("서브 타이틀")
                .content("컨텐츠")
                .boardType(BoardType.free)
                .user(user).build());
    }

    @Test
    public void 제대로_생성_됐는지_테스트() {
        User user = userRepository.findOne(Long.valueOf(1));
        assertThat(user.getName(), is("havi"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is("test@gmail.com"));

        Board board = boardRepository.findOne(Long.valueOf(1));
        assertThat(board.getTitle(), is("테스트"));
        assertThat(board.getSubTitle(), is("서브 타이틀"));
        assertThat(board.getContent(), is("컨텐츠"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }

}