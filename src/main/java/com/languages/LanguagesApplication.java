package com.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
public class LanguagesApplication {

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    WordRepository wordRepository;
    @Autowired
    NotLearnedWordRepository notLearnedWordRepository;


    public static void main(String[] args) {
        SpringApplication.run(LanguagesApplication.class, args);
    }


}
