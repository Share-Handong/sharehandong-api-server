package sharehandong.sharehandongapiserver.api.v1.share.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name="picture_test")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long id;

    @Column(name = "orig_filename")
    private String origFilename;

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filePath;

    @Builder
    public FileEntity(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}