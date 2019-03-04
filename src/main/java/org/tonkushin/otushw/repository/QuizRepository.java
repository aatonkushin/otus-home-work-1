package org.tonkushin.otushw.repository;

import org.tonkushin.otushw.model.Question;

import java.util.List;
import java.util.Locale;

/**
 * Сервис, отвечающий за чтение данных из csv файла
 */
public interface QuizRepository {
    /**
     * Читает данные из файла и возвращает список с вопросами
     * @return список с вопросами
     */
    List<Question> readQuestionsFromFile(Locale locale);
}
