package com.languages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Chapters")
public class Chapter {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "language_id")
    Language language;
    String description;

    public Chapter() {

    }
    public Chapter(String desc, Language language){
        this.description = desc;
        this.language = language;
    }
}
