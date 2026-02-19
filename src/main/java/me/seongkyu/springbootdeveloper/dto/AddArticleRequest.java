package me.seongkyu.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
//블로그 글 추가 요청
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(String author) {     //toEntity는 빌더 패턴을 사용해 DTO를 엔티티로 만들어줌
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
