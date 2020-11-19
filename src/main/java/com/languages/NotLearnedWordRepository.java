package com.languages;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
public interface NotLearnedWordRepository extends JpaRepository<NotLearnedWord, Long> {

    void deleteAll();


    List<NotLearnedWord> findAllByWordLanguageCodeAndWordChapterDescription(String language, String chapter);

    List<NotLearnedWord> findAllByWordLanguageCode(String language);

    Optional<NotLearnedWord> findByWordLanguageCodeAndWordChapterDescriptionAndWordInForeign(String language, String chapter, String inForeign);

    void deleteAllByWordLanguageCodeAndWordChapterDescription(String language, String chapter);

    @Transactional
    void deleteByWordLanguageCodeAndWordChapterDescriptionAndWordInForeign(String language, String chapter, String inForeign);
}
