package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.config.auth.LoginUser;
import org.example.config.auth.dto.SessionUser;
import org.example.domain.user.User;
import org.example.service.PostsService;
import org.example.web.dto.PostsListResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.example.web.dto.PostsResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        System.out.println("--------------IndexController: index");
        List<PostsListResponseDto> dto = postsService.findAllDesc();
        model.addAttribute("posts", dto);
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPic", user.getPicture());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/reply/save")
    public String replySave() {return "reply-save";}
}