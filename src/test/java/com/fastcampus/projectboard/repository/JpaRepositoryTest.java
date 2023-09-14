package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// @ActiveProfiles("testdb")
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) /// ch02-08 19:00

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) //2.Import는 @DataJapTest에게 JpaConfig의 존재를 알려준다.
@DataJpaTest /// 1.슬라이스 테스트 /// 4. 슬라이스테스트를 돌릴 때 모든 테스트 메소드들은 메소드 단위로 트랜잭션이 걸려있다. 트랜잭션의 기본값은 롤백으로 동작한다.
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    // 테스트에서도 생성자 주입 패턴을 사용 가능합니다. 근거는
    public JpaRepositoryTest( // 3. @DataJpaTest 안에 모든 slice test가 가지고 있는 extend with spring extension이 있다.
                              @Autowired ArticleRepository articleRepository,
                              @Autowired ArticleCommentRepository articleCommentRepository,
                              @Autowired UserAccountRepository userAccountRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestDate_whenSelecting_thenWorksFine() {
        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestDate_whenInserting_thenWorksFine() {
        // given
        long previousCount = articleRepository.count();
        System.out.println("previousCount = " + previousCount);

        // when

        // then
        assertThat(articleRepository.count())
                .isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestDate_whenUpdating_thenWorksFine() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        // when
        Article savedArticle = articleRepository.saveAndFlush(article); /// 이 내용은 롤백되긴 함.

        // then
        assertThat(savedArticle)
                .hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestDate_whenDeleting_thenWorksFine() {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();


        // when
        articleRepository.delete(article); /// 이 내용은 롤백되긴 함.

        // then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);
    }
}
