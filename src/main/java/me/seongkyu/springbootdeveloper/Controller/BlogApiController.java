package me.seongkyu.springbootdeveloper.Controller;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.Article;
import me.seongkyu.springbootdeveloper.dto.AddArticleRequest;
import me.seongkyu.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController //HTTP Response Body에 객체 데이터를 JSON형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")   //HTTP메서드가 POST일 때 요청받은 URL과 동일한 메서드와 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        //HTTP 요청할 때 응답에 해당하는 값을 @RequestBody애너붙은 객체인 AddAriticleRequest에 매핑

        Article savedArticle = blogService.save(request);
    //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)    //응답코드 201, Created를 응답하고 테이블에 저장된 객체 반환
            .body(savedArticle);
    }

}
