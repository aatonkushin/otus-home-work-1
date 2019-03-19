package org.tonkushin.otushw.controller;

/**
 * Отвечает за взаимодействие с пользователем
 */
public interface QuizController {
    /**
     * Начинает тестирование пользователя
     */
    void startQuiz();

    /**
     * Выбор языка
     */
    void selectLanguage();

    /**
     * Регистрация пользователя
     */
    void login(String username);
}
