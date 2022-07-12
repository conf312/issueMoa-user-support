package com.issuemoa.user.support.domain.file;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "file_mst")
public class FileMst {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long targetId;
    private String fileName;
    private String fileSaveName;
    private String filePath;
    private String fileWebPath;
    private int fileSize;
    private String useYn;
    private Long registerId;
    @CreatedDate
    private LocalDateTime registerTime;

    @Builder
    public FileMst(Long id, Long targetId, String fileName, String fileSaveName, String filePath, String fileWebPath, int fileSize, String useYn, Long registerId, LocalDateTime registerTime) {
        this.id = id;
        this.targetId = targetId;
        this.fileName = fileName;
        this.fileSaveName = fileSaveName;
        this.filePath = filePath;
        this.fileWebPath = fileWebPath;
        this.fileSize = fileSize;
        this.useYn = useYn;
        this.registerId = registerId;
        this.registerTime = registerTime;
    }
}
