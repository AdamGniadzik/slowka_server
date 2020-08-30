package com.languages;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository

public interface WordRepository extends JpaRepository<Word, Long> {

    void deleteAll();
    Optional<Word> findByLanguageCodeAndChapterDescriptionAndInForeign(String language, String chapter, String inForeign);
    List<Word> findAllByLanguageCodeAndChapterDescription(String language, String chapter);
    List<Word> findAllByLanguageCode(String language);

    @Transactional
    @Modifying
    @Query(value = "set names utf8; ALTER DATABASE language CHARACTER SET utf8 COLLATE utf8_general_ci;", nativeQuery = true)
    void setUtf8();

}
