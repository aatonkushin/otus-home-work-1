package org.tonkushin.otushw.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.repository.QuizRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class QuizServiceImplTest {
    private Locale locale = Locale.getDefault();

    @Test
    void hasNextQuestion() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile(locale)).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertEquals(true, service.hasNextQuestion());
    }

    @Test
    void getNextQuestion() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile(locale)).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertEquals(Question.class, service.getNextQuestion().getClass());
    }

    @Test
    void getPassedTrue() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile(locale)).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        while (service.hasNextQuestion()) {
            service.getNextQuestion().setUserAnswerNo(1);
        }

        Assertions.assertEquals(true, service.getPassed());
    }

    @Test
    void getPassedFalse() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile(locale)).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertEquals(false, service.getPassed());
    }

    @Test
    void getQuestionsNotEmpty() {
        QuizRepository repository = Mockito.mock(QuizRepository.class);
        Mockito.when(repository.readQuestionsFromFile(locale)).thenReturn(getQuestions());

        QuizService service = new QuizServiceImpl(repository);

        Assertions.assertFalse(service.getQuestions().isEmpty());
    }

    List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>(5);
        String[] arrAnswers = {"1", "2", "3"};
        List<String> answers = Arrays.asList(arrAnswers);
        questions.add(new Question("Вопрос 1", 1, answers));
        questions.add(new Question("Вопрос 2", 1, answers));
        questions.add(new Question("Вопрос 3", 1, answers));
        questions.add(new Question("Вопрос 4", 4, answers));
        questions.add(new Question("Вопрос 5", 1, answers));

        return questions;
    }
}
