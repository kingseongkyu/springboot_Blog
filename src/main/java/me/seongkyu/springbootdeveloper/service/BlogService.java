package me.seongkyu.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.Article;
import me.seongkyu.springbootdeveloper.dto.AddArticleRequest;
import me.seongkyu.springbootdeveloper.dto.UpdateArticleRequest;
import me.seongkyu.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor    //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
//빈을 생성자로 생성하는 롬북에서 지원하는 애너테이션
@Service    //해당 클래스를 빈으로 서블릿 컨테이너에 등록
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {    //save()는 add클래스에 저장된 값들을 article 데베에 저장
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        List<Article> articles = blogRepository.findAll();
        return articles;
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:"+id));
    }

    //블로그 글의 ID받은 뒤 deleteById메서드를 통해 데이터 삭제

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional      //매칭한 메서드를 하나의 트랜잭션으로
    //트랜잭션 : 데이터베이스의 데이터를 바꾸기 위한 작업의 단위 ex) 입금, 출금 묶기
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:"+id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
