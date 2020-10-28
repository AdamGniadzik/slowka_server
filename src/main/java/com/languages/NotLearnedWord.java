package com.languages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "not_learned")
public class NotLearnedWord {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @JoinColumn(name = "word_id")
    @OneToOne
    Word word;


    public NotLearnedWord() {
    }

    public NotLearnedWord(Word word) {
        System.out.print(word);
        this.word = word;
    }
    @JsonIgnore
    public Chapter getChapter() {
        return this.word.getChapter();
    }

    @JsonIgnore
    public Language getLanguage(){
        return this.word.getLanguage();
    }


}
