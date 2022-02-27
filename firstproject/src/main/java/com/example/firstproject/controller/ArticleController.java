package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

        return "";
    }

}
