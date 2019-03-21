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

    void results();

    /**
     * Пользователь зарегистрирован
     * @return true если зарегистрирован
     */
    boolean isUserLoggedIn();

    /**
     * Тест окончен
     * @return true если тест окончен
     */
    boolean isTestCompleted();
}
