package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.service.ReplyService;
import org.example.web.dto.ReplySaveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("/api/v1/reply")
    public Long save(@RequestBody ReplySaveRequestDto requestDto){
        return replyService.save(requestDto);
    }
}
