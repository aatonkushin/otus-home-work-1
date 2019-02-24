package org.tonkushin.otushw.service;

import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.repository.QuizRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Реализация бизнес-логики
 */
public class QuizServiceImpl implements QuizService {
    private QuizRepository repository;    //репозиторий для считывания файла

    private List<Question> questions;               //список вопросв
    private Iterator<Question> iterator;            //итератор для выдачи вопросов по одному

    public QuizServiceImpl(QuizRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean hasNextQuestion() {
        loadData();

        return iterator.hasNext();
    }

    @Override
    public Question getNextQuestion() {
        loadData();

        return iterator.next();
    }

    @Override
    public Boolean getPassed() {
        loadData();

        long total = questions.size();
        long passed = questions.stream().filter(Question::isCorrect).count();

        return ((float)passed / total) > 0.4f;
    }

    /**
     * Загружает данные
     */
    private void loadData() {
        if (questions == null || questions.isEmpty()) {
            questions = repository.readQuestionsFromFile();
            iterator = questions.iterator();
        }
    }
}
