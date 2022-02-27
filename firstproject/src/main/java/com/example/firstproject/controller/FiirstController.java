package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// todo: . 컨트롤러를 만들 것이다 .
@Controller
public class FiirstController {

// 1. 화면에서 받은 url path 가 컨트롤에서 받는다 .
    @GetMapping("/hi")
    public String getGreetings(){

// 2. 받은 path 를 -> templates Mustache/Html 파일로 return 한다.
      return  "greetings";  // templates/greetings.mustache , 브라우저로 전송 !
    }

// 1. 화면에서 받은 url path 가 컨트롤에서 받는다 .
    @GetMapping("/bye")
// 2. Model 를 이용하여 파라미터를 전달할 값을 셋팅한다.
    public String goodBye(Model model){
        model.addAttribute("username","무회님");
// 3. greetings2.mustache 파일에 값을 전달 하면서 리턴한다.
      return  "goodbye";  // templates/goodbye.mustache , 브라우저로 전송 !
    }




}
