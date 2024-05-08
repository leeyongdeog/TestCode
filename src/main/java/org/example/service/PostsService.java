package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.posts.Posts;
import org.example.domain.posts.PostsRepository;
import org.example.domain.user.User;
import org.example.domain.user.UserRepository;
import org.example.web.dto.*;
import org.springframework.stereotype.Service;
import org.example.web.dto.PostsUpdateRequestDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private PostsSaveRequestDto requestDto;
    private UserRepository userRepository;

    @Transactional
    public void save(PostsSaveRequestDto requestDto){
        this.requestDto = requestDto;
        postsRepository.save(requestDto.toEntity());
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        System.out.println("--------------PostsService: findAllDesc");
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found. id="+id));
        postsRepository.delete(posts);
    }

    @Transactional
    public void getUserData(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Not Found Email = "+email));

    }
}
