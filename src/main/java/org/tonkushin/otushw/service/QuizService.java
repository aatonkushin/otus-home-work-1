package org.tonkushin.otushw.service;

import org.tonkushin.otushw.model.Question;

/**
 * Уровень бизнес-логики
 */
public interface QuizService {
    /**
     * Проверяет: есть ли ещё вопросы
     * @return true - если есть
     */
    Boolean hasNextQuestion();

    /**
     * Возвращает вопрос
     * @return вопрос
     */
    Question getNextQuestion();

    /**
     * Проверяет: сдан ли тест
     * @return true - если тест сдан
     */
    Boolean getPassed();
}
