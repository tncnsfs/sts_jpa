package com.example.firstproject.dto;

// 1, 필드명을 정의 한다, title, content
// 2, 정의한 필드명은 html파일 form 태그의 name 속성과 이름이 같아야 한다.
// 3, 생성자를 생성한다.
// 4, toString method 를 생성한다.

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long   id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
