package me.seongkyu.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)      //엔티티의 생성 및 수정 시간을 자동으로 감시, 기록
@Entity
@Table(name = "article")
@Getter //@모든 필드에 대한 접근자 메서드를 만들 수 있게 됨
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //@선언해서 기본생성자를 생성했고
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;


    @CreatedDate     //엔티티가 생성될 때 생성기간 created_at 컬럼에 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate  //엔티티가 수정될 때 마지막으로 수정된 시간 updated_at 컬럼에 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }



    /* protected Article() {
    }
     //게터
    public Long getId() {   //필드의 값을 가져오는 게터 메서드들 다른 에너테이션으로 대체
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;

    }
    */
}

