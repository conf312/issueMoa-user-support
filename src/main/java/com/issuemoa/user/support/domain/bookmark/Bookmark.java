package com.issuemoa.user.support.domain.bookmark;

import com.issuemoa.user.support.domain.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "bookmark")
public class Bookmark extends BaseTime {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private Long userId;
    private Long targetId;

    @Builder
    public Bookmark(Long id, String title, Long userId, Long targetId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.targetId = targetId;
    }

    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String title;
        private Long userId;
        private Long targetId;

        public Bookmark toEntity() {
            return Bookmark.builder()
                    .title(this.title)
                    .userId(this.userId)
                    .targetId(this.targetId)
                    .build();
        }
    }

    @Getter
    public static class Response {
        private Long id;
        private String title;
        private Long userId;
        private Long targetId;
        private String registerTime;
        private String modifyTime;

        public Response(Object o) {
            Bookmark bookmark = (Bookmark) o;
            this.id = bookmark.id;
            this.title = bookmark.title;
            this.userId = bookmark.userId;
            this.targetId = bookmark.targetId;
            this.registerTime = toStringDateTime(bookmark.getRegisterTime());
            this.modifyTime = toStringDateTime(bookmark.getModifyTime());
        }
    }
}
