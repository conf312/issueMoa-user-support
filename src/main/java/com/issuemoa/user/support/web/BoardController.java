package com.issuemoa.user.support.web;

import com.issuemoa.user.support.domain.board.Board;
import com.issuemoa.user.support.message.RestMessage;
import com.issuemoa.user.support.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/support")
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/notice/list")
    public ResponseEntity findAll(Board.Request request,
                                  @RequestParam(required = false, defaultValue = "0") Integer page,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok().headers(new HttpHeaders()).body(new RestMessage(HttpStatus.OK, "", boardService.findAll(request, page, pageSize))); }
}
