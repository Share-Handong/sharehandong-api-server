package sharehandong.sharehandongapiserver.api.v1.share.service;

import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.FileEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.FileRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.FileDto;

import javax.transaction.Transactional;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id) {
        FileEntity file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }
}