package com.languages;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {


    public void deleteAll();
    Optional<Chapter> findByLanguageCodeAndDescription(String langugage, String description);
    List<Chapter> findAllByLanguageCode(String language);
}
