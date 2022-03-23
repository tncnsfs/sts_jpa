package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    // 1. 받을 url 을 정의 한다.
    // 2. 받은 url 을 new templates 에 전달 한다.
    // 3. 입력창 화면  new.mustache 를 화면에 리턴한다.
    @GetMapping("/articles/news")
    public String newTemp() {
        return "articles/new";
    }

    // 1. news/Form 화면에서 던진 acition = "articles/create"  내용을 받는다 .
    // 2, DTO class 파일을 만든다. <form> 의 name 속성과 필드명이 같아야 한다.
    // 3, DTO 파라미터를 createMethod 에 통과하게 한다.
    // 4, 받은 DTO 내용을 로그에 찍어 본다.
    @PostMapping("/articles/createlog")
    public String createMethod(ArticleForm articleForm) {
        System.out.println("test_starting" + articleForm.toString());

        return "";
    }

    // 1. news/Form 화면에서 던진 acition = "articles/create"  내용을 받는다 .
    // 2, DTO class 파일을 만든다. <form> 의 name 속성과 필드명이 같아야 한다.
    // 3, DTO 파라미터를 createMethod 에 통과하게 한다.
    // 4, 받은 DTO 내용을 로그에 찍어 본다.

    // 5, DTO를 Entity 로 변환한다.
    // 6, Entity 를 repository 에 넣는다 .
    // 7, repository 를 이용해 JPA 의 DB에 insert 한다 . (save 함수 사용)
    // 8, DB 를 확인해본다 . ( log에서 h2 조회)

    @PostMapping("/articles/create")
    public String createRepository(ArticleForm form) {
//        System.out.println("DTO : " + form.toString());
        log.info("DTO : " + form.toString());

        Article articleEntity = form.toEntity();
//        System.out.println("ENTITY : " +articleEntity.toString());
        log.info("ENTITY : " +articleEntity.toString());

        Article saved = articleRepository.save(articleEntity);
//        System.out.println("REPOSITORY : " +saved.toString());
        log.info("REPOSITORY : " +saved.toString());
        log.debug("REPOSITORY debug : " +saved.toString());

        return "redirect:/articles/" + saved.getId(); // 상세페이지
    }

    // 1. detail 페이지 만들기 ,
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : {} " , id);

        // 1, id로 데이터를 가져옴
        //    Optional<Article> articleEntity = articleRepository.findById(id);  // java 8 버전
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2, 가져온 데이터를 모델에 등록, 이러면 화면에 article 사용될수 있다 .
        model.addAttribute("article", articleEntity);

        // 3, 보여줄 페이지를 설정!
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model){
        // 1: 모든 article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가져온 Article 묶음을 뷰로 전달 !
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }


    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // Model 에 데이터를 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지설정 !
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1 , DTO -> ENTITY
        Article articleEntity = form.toEntity();
//
        //2, ENTITY IS SAVE
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        log.info("target        , DB 에서 원본데이터 가져오기   : {} " , target);
        log.info("articleEntity , DB 에   수정할 데이터 넣기 전 : {} " , articleEntity);


        if (target != null){
//        3, SAVEDATA -> UPDATE REDIRECT VIEW
             articleRepository.save(articleEntity);
        }

//        4, 상세페이지로 이동한다 .
        return "redirect:/articles/" + articleEntity.getId();
    }



}
