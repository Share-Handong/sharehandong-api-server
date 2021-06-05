package sharehandong.sharehandongapiserver.api.v1.share.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.BoardRepository;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;
import sharehandong.sharehandongapiserver.api.v1.user.domain.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShareService {
    private ShareRepository shareRepository;
    @Autowired
    UserRepository userRepository;


    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Transactional
    public Long savePost(ShareDto shareDto, String userName) {
        Long id = userRepository.findByUserName(userName).getIdx();
        shareDto.setWriter(id);
        return shareRepository.save(shareDto.toEntity()).getIdx();
    }

    @Transactional
    public List<ShareDto> getShareList() {
        List<ShareEntity> shareList = shareRepository.findAll();
        List<ShareDto> shareDtoList = new ArrayList<>();

        for(ShareEntity share : shareList) {
            Long id = share.getWriter();
            String userName = userRepository.findByIdx(id).getUserName();
//            ShareDto shareDto = ShareDto.builder()
//                    .idx(share.getIdx())
//                    .writer(share.getWriter())
//                    .title(share.getTitle())
//                    .catego(share.getCatego())
//                    .state(share.getState())
//                    .content(share.getContent())
//                    .createdAt(share.getCreatedAt())
//                    .build();

            ShareDto shareDto = new ShareDto(
                    share.getIdx(),
                    share.getTitle(),
                    share.getContent(),
                    share.getWriter(),
                    share.getCreatedAt(),
                    share.getUpdatedAt(),
                    share.getCatego(),
                    share.getState(),
                    userName
            );
            shareDtoList.add(shareDto);
        }
        return shareDtoList;
    }

    @Transactional
    public List<ShareDto> getMyShareList(String userName) {
        UserEntity temp = userRepository.findByUserName(userName);
        Long id = userRepository.findByUserName(userName).getIdx();
        List<ShareEntity> shareList = shareRepository.findAllByWriter(id);
        List<ShareDto> shareDtoList = new ArrayList<>();

        for(ShareEntity share : shareList) {

//            ShareDto shareDto = ShareDto.builder()
//                    .idx(share.getIdx())
//                    .writer(share.getWriter())
//                    .title(share.getTitle())
//                    .catego(share.getCatego())
//                    .state(share.getState())
//                    .content(share.getContent())
//                    .createdAt(share.getCreatedAt())
//                    .build();

            ShareDto shareDto = new ShareDto(
                    share.getIdx(),
                    share.getTitle(),
                    share.getContent(),
                    share.getWriter(),
                    share.getCreatedAt(),
                    share.getUpdatedAt(),
                    share.getCatego(),
                    share.getState(),
                    userName
            );
            shareDtoList.add(shareDto);
        }
        return shareDtoList;
    }

    @Transactional
    public ShareDto getPost(Long id) {
        ShareEntity share = shareRepository.findById(id).get();
        Long idx = share.getWriter();
        String userName = userRepository.findByIdx(idx).getUserName();

//        ShareDto shareDto = ShareDto.builder()
//                .idx(share.getIdx())
//                .writer(share.getWriter())
//                .title(share.getTitle())
//                .catego(share.getCatego())
//                .state(share.getState())
//                .content(share.getContent())
//                .createdAt(share.getCreatedAt())
//                .build();
        ShareDto shareDto = new ShareDto(
                share.getIdx(),
                share.getTitle(),
                share.getContent(),
                share.getWriter(),
                share.getCreatedAt(),
                share.getUpdatedAt(),
                share.getCatego(),
                share.getState(),
                userName
        );
        return shareDto;
    }



    @Transactional
    public void deletePost(Long id) {
        shareRepository.deleteById(id);
    }

    @Transactional
    public Long updatePost(Long id,ShareDto shareDto) {
        ShareEntity defaultShareDto  = shareRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당글 존재하지 않습니다.")
        );

        defaultShareDto.setCatego(shareDto.getCatego());
        defaultShareDto.setContent(shareDto.getContent());
        defaultShareDto.setState(shareDto.getState());
        defaultShareDto.setTitle(shareDto.getTitle());
        return id;
    }


}

