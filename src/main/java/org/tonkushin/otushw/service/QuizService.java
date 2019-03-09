package org.tonkushin.otushw.service;

import org.tonkushin.otushw.model.Question;

import java.util.List;
import java.util.Locale;

/**
 * Уровень бизнес-логики
 */
public interface QuizService {
    /**
     * Проверяет: есть ли ещё вопросы
     *
     * @return true - если есть
     */
    Boolean hasNextQuestion();

    /**
     * Возвращает вопрос
     *
     * @return вопрос
     */
    Question getNextQuestion();

    /**
     * Проверяет: сдан ли тест
     *
     * @return true - если тест сдан
     */
    Boolean getPassed();

    /**
     * На случай - если понадобятся сразу все вопросы
     *
     * @return все вопросы
     */
    List<Question> getQuestions();

    /**
     * Устанавливает локаль, для которой необходимо прочитать файл и вернуть вопросы.
     *
     * @param locale локаль
     */
    void setLocale(Locale locale);
}
