package sharehandong.sharehandongapiserver.api.v1.share.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.BoardRepository;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShareService {
    private ShareRepository shareRepository;


    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @javax.transaction.Transactional
    public Long savePost(ShareDto shareDto) {

        return shareRepository.save(shareDto.toEntity()).getIdx();
    }

    @Transactional
    public List<ShareDto> getShareList() {
        List<ShareEntity> shareList = shareRepository.findAll();
        List<ShareDto> shareDtoList = new ArrayList<>();

        for(ShareEntity share : shareList) {
            ShareDto shareDto = ShareDto.builder()
                    .idx(share.getIdx())
                    .writer(share.getWriter())
                    .title(share.getTitle())
                    .content(share.getContent())
                    .createdAt(share.getCreatedAt())
                    .build();
            shareDtoList.add(shareDto);
        }
        return shareDtoList;
    }

    @javax.transaction.Transactional
    public ShareDto getPost(Long id) {
        ShareEntity share = shareRepository.findById(id).get();

        ShareDto shareDto = ShareDto.builder()
                .idx(share.getIdx())
                .writer(share.getWriter())
                .title(share.getTitle())
                .content(share.getContent())
                .createdAt(share.getCreatedAt())
                .build();
        return shareDto;
    }

    @javax.transaction.Transactional
    public void deletePost(Long id) {
        shareRepository.deleteById(id);
    }
}
