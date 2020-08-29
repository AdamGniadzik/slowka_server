package com.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class WebController {

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    WordRepository wordRepository;
    @Autowired
    NotLearnedWordRepository notLearnedWordRepository;

    @GetMapping("/reload")
    public void reload() {
        File rootPath = new File("src\\main\\resources\\userLangFolder");
        System.out.println(rootPath.canRead());
        System.out.println(rootPath.isDirectory());
        File files[] = rootPath.listFiles();
        String languages[] = rootPath.list();
        for (String languageName : languages) {

            Language language = languageRepository.findByCode(languageName).orElseGet(() -> languageRepository.save(new Language(languageName)));
            File langDirectory = new File("src\\main\\resources\\userLangFolder\\" + languageName);
            String chapters[] = langDirectory.list();
            for (String chapterFileName : chapters) {
                String chapterName = chapterFileName.substring(0, chapterFileName.indexOf('.'));
                Chapter chapter = chapterRepository.findByLanguageCodeAndDescription(languageName,chapterName).orElseGet(() -> chapterRepository.save(new Chapter(chapterName, language)));
                File chapterFile = new File(langDirectory.getAbsolutePath() + "\\" + chapterFileName);    //creates a new file instance
                try {
                    BufferedReader chapterReader = new BufferedReader(new FileReader(chapterFile));
                    String line;
                    while ((line = chapterReader.readLine()) != null) {
                        String[] content = line.split(";");
                        wordRepository.findByLanguageCodeAndChapterDescriptionAndInForeign(languageName, chapterName,content[0]).ifPresentOrElse(word -> {
                        }, () -> wordRepository.save(new Word(language, chapter, content[0], content[1])));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/getAllWords")
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @GetMapping("/deleteAll")
    public void deleteAll() {
        wordRepository.deleteAll();
        chapterRepository.deleteAll();
        languageRepository.deleteAll();
    }

    @GetMapping("/getChaptersByLanguage")
    public List<Chapter> getChaptersByLanguage(@RequestParam String language) {
        System.out.println(chapterRepository.findAllByLanguageCode(language));
        return chapterRepository.findAllByLanguageCode(language);
    }
    @GetMapping("/getNotLearnedChaptersByLanguage")
    public List<Chapter> getNotLearnedChaptersByLanguage(@RequestParam String language){
     return  notLearnedWordRepository.findAllByWordLanguageCode(language).stream().map(NotLearnedWord::getChapter).distinct().collect(Collectors.toList());
    }
    @GetMapping("/getNotLearnedWordsByChapterAndLanguage")
    public List<Word> getNotLearnedWordsByLanguageAndChapter(@RequestParam String language, @RequestParam String chapter){
        return notLearnedWordRepository.findAllByWordLanguageCodeAndWordChapterDescription(language,chapter).stream().map(NotLearnedWord::getWord).collect(Collectors.toList());
    }

    @GetMapping("/getAllWordsByChapter")
    public List<Word> getWordsByLanguageAndChapter( @RequestParam String language, @RequestParam String chapter) {
        List<Word> result = wordRepository.findAllByLanguageCodeAndChapterDescription(language, chapter);
        Collections.shuffle(result);
        return result;
    }

    @GetMapping("/getAllWordsByLanguage")
    public List<Word> getWordsByLanguage(@RequestParam String language) {
        List<Word> result = wordRepository.findAllByLanguageCode(language);
        Collections.shuffle(result);
        return result;
    }

    @PostMapping("/addNotLearnedWord")
    public void addNotLearnedWord(@RequestParam String language, @RequestParam String chapter, @RequestParam String inForeign) {
        wordRepository.findByLanguageCodeAndChapterDescriptionAndInForeign(language,chapter,inForeign).ifPresent(word -> notLearnedWordRepository.save(new NotLearnedWord(word)));
    }

    @GetMapping("/getAllNotLearnedWordsByChapter")
    public List<Word> getAllNotLearnedWordsByChapter(@RequestParam String language, @RequestParam String chapter) {
        List<Word> result = notLearnedWordRepository.findAllByWordLanguageCodeAndWordChapterDescription(language, chapter).stream().map(NotLearnedWord::getWord).collect(Collectors.toList());
        Collections.shuffle(result);
        return result;
    }

    @GetMapping("/getAllNotLearnedLanguages")
    public List<Language> getAllNotLearnedLanguage(){
        return notLearnedWordRepository.findAll().stream().map(NotLearnedWord::getLanguage).distinct().collect(Collectors.toList());
    }
    @GetMapping("/getAllNotLearnedWords")
    public List<Word> getAllNotLearnedWords() {
        List<Word> result = notLearnedWordRepository.findAll().stream().map(NotLearnedWord::getWord).collect(Collectors.toList());
        Collections.shuffle(result);
        return result;
    }

    @GetMapping("/deleteAllNotLearnedWordsByChapter")
    public void deleteAllNotLearnedWordsByLanguageAndChapter(@RequestParam String language, @RequestParam String chapter) {
        notLearnedWordRepository.deleteAllByWordLanguageCodeAndWordChapterDescription(language, chapter);
    }

    @GetMapping("/deleteAllNotLearnedWords")
    public void deleteAllNotLearnedWords() {
        notLearnedWordRepository.deleteAll();
    }


    @GetMapping("/getAllLanguages")
    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }

    @PostMapping("/sendUnknownWord")
    public void addNewNotLearnedWord(@RequestParam String language, @RequestParam String chapter, @RequestParam String word) {
        notLearnedWordRepository.findByWordLanguageCodeAndWordChapterDescriptionAndWordInForeign(language,chapter,word)
                .ifPresentOrElse(notLearnedWord -> {},
                () -> notLearnedWordRepository.save(new NotLearnedWord(wordRepository.findByLanguageCodeAndChapterDescriptionAndInForeign(language,chapter,word).get())));
    }

    @PostMapping("/deleteUnknownWord")
    public void deleteNewNotLearnedWord(@RequestParam String language, @RequestParam String chapter, @RequestParam String word) {
        notLearnedWordRepository.deleteByWordLanguageCodeAndWordChapterDescriptionAndWordInForeign(language,chapter,word);
    }
}