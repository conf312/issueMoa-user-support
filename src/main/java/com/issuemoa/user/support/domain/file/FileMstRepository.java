package com.issuemoa.user.support.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileMstRepository extends JpaRepository<FileMst, Long> {
    public List<FileMst> findByTargetIdAndUseYn(Long targetId, String useYn);
}
