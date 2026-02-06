package me.seongkyu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.Article;
import me.seongkyu.springbootdeveloper.dto.AddArticleRequest;
import me.seongkyu.springbootdeveloper.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor    //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
//빈을 생성자로 생성하는 롬북에서 지원하는 애너테이션
@Service    //해당 클래스를 빈으로 서블릿 컨테이너에 등록
public class BlogService {

    private final ArticleRepository articleRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {    //save()는 add클래스에 저장된 값들을 article 데베에 저장
        return articleRepository.save(request.toEntity());
    }
}
