package com.issuemoa.user.support.service.board;

import com.issuemoa.user.support.domain.board.Board;
import com.issuemoa.user.support.domain.board.BoardRepository;
import com.issuemoa.user.support.domain.board.QBoard;
import com.issuemoa.user.support.service.file.FileMstService;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileMstService fileMstService;
    private final JPAQueryFactory jpaQueryFactory;

    private QBoard board = QBoard.board;

    public BooleanExpression eqType(String type) {
        if (!StringUtils.hasText(type)) return null;
        return board.type.eq(type);
    }

    public BooleanExpression containsSearch(String serach) {
        if (!StringUtils.hasText(serach)) return null;
        return board.title.containsIgnoreCase(serach).or(board.contents.containsIgnoreCase(serach));
    }

    public HashMap<String, Object> findAll(Board.Request request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

       List<Board.Response> list = jpaQueryFactory
                .select(Projections.constructor(Board.Response.class,
                    board.id,
                    board.type,
                    board.title,
                    board.readCnt,
                    board.registerTime
                ))
                .from(board)
                .where(
                    containsSearch(request.getSearch()),
                    eqType(request.getType()))
                .offset(page)
                .limit(pageSize)
                .orderBy(board.registerTime.desc())
                .fetch();

        Long totalCnt = (long) jpaQueryFactory.select(board.count()).from(board)
                .where(
                    containsSearch(request.getSearch()),
                    eqType(request.getType()))
                .fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / pageSize);
        totalPage = totalPage == 0 ? 1 : totalPage;

        resultMap.put("list", list);
        resultMap.put("request", request);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }

    @Transactional
    public HashMap<String, Object> findById(Long id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        Board.Response info = jpaQueryFactory.select(Projections.constructor(Board.Response.class,
            board.id,
            board.type,
            board.startDate,
            board.endDate,
            board.allTimeYn,
            board.title,
            board.contents,
            board.videoUrl,
            board.readCnt,
            board.registerTime
        ))
        .from(board)
        .where(board.id.eq(id))
        .fetchOne();

        jpaQueryFactory.update(board)
            .set(board.readCnt, info.getReadCnt() + 1)
            .where(board.id.eq(id))
            .execute();

        resultMap.put("info", info);
        resultMap.put("fileList", fileMstService.findByTargetIdAndUseYn(id,"Y"));

        return resultMap;
    }
}
