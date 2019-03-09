package org.tonkushin.otushw.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.util.Locale;

class QuizRepositoryImplTest {
    private String filename = "questions_%s.csv";
    private Locale locale = Locale.getDefault();
    private Locale localeEn = Locale.forLanguageTag("en");

    /**
     * Для русского
     */
    @Test
    void readQuestionsFromFileNotNullTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename);

        Assertions.assertNotEquals(null, repository.readQuestionsFromFile(locale));
    }

    /**
     * Для русского
     */
    @Test
    void readQuestionsFromFileNotEmptyTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename);

        Assertions.assertNotEquals(0, repository.readQuestionsFromFile(locale).size());
    }

    /**
     * Для английского
     */
    @Test
    void readQuestionsFromFileEnNotNullTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename);

        Assertions.assertNotEquals(null, repository.readQuestionsFromFile(localeEn));
    }

    /**
     * Для английского
     */
    @Test
    void readQuestionsFromFileEnNotEmptyTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename);

        Assertions.assertNotEquals(0, repository.readQuestionsFromFile(localeEn).size());
    }
}
