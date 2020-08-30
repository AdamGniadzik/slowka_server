package com.languages;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    void deleteAll();
    Optional<Word> findByLanguageCodeAndChapterDescriptionAndInForeign(String language, String chapter, String inForeign);
    List<Word> findAllByLanguageCodeAndChapterDescription(String language, String chapter);
    List<Word> findAllByLanguageCode(String language);

    @Query("set names 'utf8'")
    void setUtf8();

}
