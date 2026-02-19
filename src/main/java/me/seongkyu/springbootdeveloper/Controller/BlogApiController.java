package me.seongkyu.springbootdeveloper.Controller;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.Article;
import me.seongkyu.springbootdeveloper.dto.AddArticleRequest;
import me.seongkyu.springbootdeveloper.dto.ArticleResponse;
import me.seongkyu.springbootdeveloper.dto.UpdateArticleRequest;
import me.seongkyu.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP Response Body에 객체 데이터를 JSON형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")   //HTTP메서드가 POST일 때 요청받은 URL과 동일한 메서드와 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        //HTTP 요청할 때 응답에 해당하는 값을 @RequestBody애너붙은 객체인 AddAriticleRequest에 매핑

        Article savedArticle = blogService.save(request, principal.getName());
    //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)    //응답코드 201, Created를 응답하고 테이블에 저장된 객체 반환
            .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id) {   //URL에서 값을 가져오는 에너테이션
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")    //delete요청 들어오면 id에 해당하는 값이 Path에너테이션을 통해 들어옴
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")       //put요청이 오면 body 정보가 request로 넘어옴
                                            // -> 다시 서비스 클래스의 update()메서드에 id와 request를 넘겨줌
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id,
        @RequestBody UpdateArticleRequest request) {
      Article updatedArticle = blogService.update(id, request);

      return ResponseEntity.ok()
              .body(updatedArticle);

    }

}
//3을 GET요청 받으면 id에 3이 들어오고 findById 메서드로 넘어가 3번 블로그 글을 찾음, 3번 글의 정보를 body에 담아 웹브라우저 전송
