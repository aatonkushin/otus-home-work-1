package org.tonkushin.otushw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.repository.QuizRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Реализация бизнес-логики
 */
@Service
public class QuizServiceImpl implements QuizService {
    private QuizRepository repository;    //репозиторий для считывания файла

    private List<Question> questions;               //список вопросв
    private Iterator<Question> iterator;            //итератор для выдачи вопросов по одному
    private Locale locale = Locale.getDefault();    //локаль для чтения соответсвующего файла

    @Autowired
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

    @Override
    public List<Question> getQuestions() {
        loadData();
        return this.questions;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Загружает данные
     */
    private void loadData() {
        if (questions == null || questions.isEmpty()) {
            questions = repository.readQuestionsFromFile(locale);
            iterator = questions.iterator();
        }
    }
}
