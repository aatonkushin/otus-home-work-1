package org.tonkushin.otushw.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;

class QuizServiceImplTest {

    @Test
    void hasNextQuestion() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile()).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertEquals(true, service.hasNextQuestion());
    }

    @Test
    void getNextQuestion() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile()).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertEquals(Question.class, service.getNextQuestion().getClass());
    }

    @Test
    void getPassedTrue() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile()).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        while (service.hasNextQuestion()){
            service.getNextQuestion().setUserAnswerNo(1);
        }

        Assertions.assertEquals(true, service.getPassed());
    }

    @Test
    void getPassedFalse() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile()).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertEquals(false, service.getPassed());
    }

    List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>(5);
        questions.add(new Question("Вопрос 1", "1", "2", "3", 1));
        questions.add(new Question("Вопрос 2", "1", "2", "3", 1));
        questions.add(new Question("Вопрос 2", "1", "2", "3", 1));
        questions.add(new Question("Вопрос 2", "1", "2", "3", 1));
        questions.add(new Question("Вопрос 2", "1", "2", "3", 1));

        return questions;
    }
}
