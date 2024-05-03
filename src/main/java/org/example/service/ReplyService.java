package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.posts.Posts;
import org.example.domain.posts.PostsRepository;
import org.example.web.dto.ReplySaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final PostsRepository replyRepository;
    private ReplySaveRequestDto requestDto;

    @Transactional
    public Long save(ReplySaveRequestDto requestDto){
        this.requestDto = requestDto;
        return replyRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        Posts reply = replyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found. id="+id));
        replyRepository.delete(reply);
    }
}
