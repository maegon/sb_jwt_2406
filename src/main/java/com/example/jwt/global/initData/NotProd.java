package com.example.jwt.global.initData;

import com.example.jwt.domain.article.entity.Article;
import com.example.jwt.domain.article.service.ArticleService;
import com.example.jwt.domain.member.entity.Member;
import com.example.jwt.domain.member.service.MemberService;
import com.example.jwt.global.rsData.RsData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member amdin = memberService.join("admin", password, "admin@test.com");
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");

            articleService.write(amdin, "제목1", "내용1");
            articleService.write(amdin, "제목2", "내용2");
            articleService.write(member1, "제목3", "내용3");
            articleService.write(member1, "제목4", "내용4");
            articleService.write(member2, "제목5", "내용5");
            articleService.write(member2, "제목6", "내용6");
        };
    }
}