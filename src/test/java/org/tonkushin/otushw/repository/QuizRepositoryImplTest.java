package org.tonkushin.otushw.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuizRepositoryImplTest {
    private String filename = "questions.csv";

    @Test
    void readQuestionsFromFileNotNullTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename);

        Assertions.assertNotEquals(null, repository.readQuestionsFromFile());
    }

    @Test
    void readQuestionsFromFileNotEmptyTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename);

        Assertions.assertNotEquals(0, repository.readQuestionsFromFile().size());
    }
}
