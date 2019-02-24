package org.tonkushin.otushw.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuizRepositoryImplTest {
    private String filename = "questions.csv";
    private String[] columns = {"question", "answer1", "answer2", "answer3", "correctAnswerNo"};

    @Test
    void readQuestionsFromFileNotNullTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename, columns);

        Assertions.assertNotEquals(null, repository.readQuestionsFromFile());
    }

    @Test
    void readQuestionsFromFileNotEmptyTest() {

        QuizRepository repository = new QuizRepositoryImpl(filename, columns);

        Assertions.assertNotEquals(0, repository.readQuestionsFromFile().size());
    }
}
