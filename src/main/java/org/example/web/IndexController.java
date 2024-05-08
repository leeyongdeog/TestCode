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
        List<PostsListResponseDto> dto = postsService.findAllDesc();
        model.addAttribute("posts", dto);
        if(user != null){
            System.out.println("name: " + user.getName());
            System.out.println("email: " + user.getEmail());
            System.out.println("picture: " + user.getPicture());
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPic", user.getPicture());
            model.addAttribute("userEmail", user.getEmail());
        }
        System.out.println("index in");
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