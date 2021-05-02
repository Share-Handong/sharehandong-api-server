package sharehandong.sharehandongapiserver.api.v1.share.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;


@AllArgsConstructor
@Service
public class ShareService {
    private ShareRepository shareRepository;
    @Transactional
    public Long savePost(ShareDto shareDto) {
        return shareRepository.save(shareDto.toEntity()).getIdx();
    }
}
