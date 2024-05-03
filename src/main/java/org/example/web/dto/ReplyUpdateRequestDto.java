package org.example.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyUpdateRequestDto {
    private String content;

    @Builder
    public ReplyUpdateRequestDto(String content){
        this.content = content;
    }
}
