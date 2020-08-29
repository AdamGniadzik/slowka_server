package com.languages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Languages" )
@Entity
public class Language {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String code;

    public Language() {
    }
    public Language(String code){
        this.code = code;
    }
}
