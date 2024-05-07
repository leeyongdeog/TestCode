package org.example.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) // 실행할 테스트 케이스 클래스를 지정하는데 사용
@WebMvcTest(controllers = HelloController.class) //  Spring MVC 컨트롤러를 테스트하는 데 사용
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

//    @Test
//    public void sayHello() throws Exception{
//        String hello = "hello";
//        // MockMvc: Spring MVC 테스트를 위한 클래스로,
//        // 실제 HTTP 요청을 수행하지 않고도 컨트롤러의 동작을 시뮬레이션하고 검증할 수 있는 방법을 제공.
//        // andExpect(): 요청에대한 응답을 검증 해주는 메서드
//        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
//    }
//
//    @Test
//    public void getHelloDto() throws Exception{
//        String name = "hello";
//        int amount = 100;
//
//        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(name)))
//                .andExpect(jsonPath("$.amount", is(amount)));
//
//    }
}
