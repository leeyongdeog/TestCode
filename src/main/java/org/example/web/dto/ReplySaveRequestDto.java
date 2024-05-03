package org.example.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private Long parent;
    private String content;

    @Builder
    public ReplySaveRequestDto(Long parent, String content){
        this.parent = parent;
        this.content = content;
    }

    public Posts toEntity(){
        return Posts.builder().parent(parent).content(content).build();
    }
}
