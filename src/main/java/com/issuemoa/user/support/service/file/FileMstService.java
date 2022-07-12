package com.issuemoa.user.support.service.file;

import com.issuemoa.user.support.domain.file.FileMst;
import com.issuemoa.user.support.domain.file.FileMstRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class FileMstService {
    private final FileMstRepository fileMstRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public FileMst findById(Long id) {
        return fileMstRepository.findById(id).get();
    }

    public List<FileMst> findByTargetIdAndUseYn(Long targetId, String useYn) {
        return fileMstRepository.findByTargetIdAndUseYn(targetId, useYn);
    }
}
