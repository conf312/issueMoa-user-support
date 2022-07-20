package com.issuemoa.user.support.service.bookmark;

import com.issuemoa.user.support.domain.bookmark.Bookmark;
import com.issuemoa.user.support.domain.bookmark.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public Long save(Bookmark.Request request) {
        return bookmarkRepository.save(request.toEntity()).getId();
    }

    public Page<Bookmark.Response> findByUserId(Long userId, Integer page, Integer pageSize) {
        return bookmarkRepository.findByUserId(userId, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "registerTime")))
                .map(Bookmark.Response::new);
    }

    public void deleteById(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
