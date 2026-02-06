package me.seongkyu.springbootdeveloper.Controller;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.Article;
import me.seongkyu.springbootdeveloper.dto.AddArticleRequest;
import me.seongkyu.springbootdeveloper.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController //HTTP Response Body에 객체 데이터를 JSON형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);


    }

}
