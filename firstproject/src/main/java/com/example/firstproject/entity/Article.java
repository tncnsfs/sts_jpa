package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@ToString
public class Article {
    @Id
    @GeneratedValue // 자동으로 id 값을 생성 , 1,2,3 ...
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
