package com.languages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Words")
public class Word {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    Chapter chapter;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    Language language;

    String inPolish;
    String inForeign;

    public Word() {
    }
    public Word(Language language, Chapter chapter, String inForeign, String inPolish){
        this.language= language;
        this.chapter = chapter;
        this.inForeign = inForeign;
        this.inPolish = inPolish;
    }
    public Word(Word word){
        this.language = word.language;
        this.chapter = word.chapter;
        this.inPolish = word.inPolish;
        this.inForeign = word.inForeign;
    }

}
