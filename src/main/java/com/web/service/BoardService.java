package com.web.service;

import com.web.domain.Board;
import com.web.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by KimYJ on 2017-07-13.
 */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<Board> boardPage = boardRepository.findAll(pageable);
        return new PageImpl<>(boardPage.getContent(), pageable, boardPage.getTotalElements());
    }

    public Board findBoardByIdx(Long idx) {
        return boardRepository.findOne(idx);
    }

    public Board saveAndUpdateBoard(Board board) {
        return boardRepository.save(board);
    }
}