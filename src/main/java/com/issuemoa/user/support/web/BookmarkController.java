package com.issuemoa.user.support.web;

import com.issuemoa.user.support.domain.bookmark.Bookmark;
import com.issuemoa.user.support.message.RestMessage;
import com.issuemoa.user.support.service.bookmark.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/support")
@Controller
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark/save")
    public ResponseEntity<RestMessage> save(Bookmark.Request request) {
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, bookmarkService.save(request)));
    }

    @GetMapping("/bookmark/findByUserId")
    public ResponseEntity<RestMessage> findByUserId(@RequestParam Long userId,
                                                @RequestParam(required = false, defaultValue = "0") Integer page,
                                                @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, bookmarkService.findByUserId(userId, page, pageSize)));
    }

    @PostMapping("/bookmark/deleteById")
    public ResponseEntity<RestMessage> deleteById(Bookmark.Request request) {
        bookmarkService.deleteById(request.getId());
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(new RestMessage(HttpStatus.OK, ""));
    }
}
