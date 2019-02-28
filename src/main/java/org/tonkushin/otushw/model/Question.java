package org.tonkushin.otushw.model;

import java.util.List;

/**
 * Класс сущности, представляющей вопрос и варианты ответов на него
 */
public class Question {
    private String question;            //вопрос
    private Integer correctAnswerNo;    //номер правильного ответа
    private Integer userAnswerNo = -1;  //ответ пользователя
    private List<String> answers;       //варианты ответов

    /**
     * вопрос
     * @return вопрос
     */
    public String getQuestion() {
        return question;
    }

    /**
     * вопрос
     * @param question вопрос
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Номер правильного ответа
     * @return номер правильного ответа
     */
    public Integer getCorrectAnswerNo() {
        return correctAnswerNo;
    }

    /**
     * Номер правильного ответа
     * @param correctAnswerNo номер правильного ответа
     */
    public void setCorrectAnswerNo(Integer correctAnswerNo) {
        this.correctAnswerNo = correctAnswerNo;
    }

    /**
     * Ответ пользователя
     * @return Ответ пользователя
     */
    public Integer getUserAnswerNo() {
        return userAnswerNo;
    }

    /**
     * Ответ пользователя
     * @param userAnswerNo Ответ пользователя
     */
    public void setUserAnswerNo(Integer userAnswerNo) {
        this.userAnswerNo = userAnswerNo;
    }

    /**
     * Варианты ответов
     * @return варианты ответов
     */
    public List<String> getAnswers() {
        return answers;
    }

    /**
     * Варианты ответов
     * @param answers варианты ответов
     */
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    /**
     * Правильность ответа пользователя
     * @return true - если ответ правильный
     */
    public boolean isCorrect() {
        return correctAnswerNo.equals(userAnswerNo);
    }

    /**
     * Конструктор по умолчанию
     */
    public Question() {
    }

    /**
     * Конструктор с параметрами для быстрой иницализацими
     * @param question вапрос
     * @param correctAnswerNo номер правильного ответа
     */
    public Question(String question, Integer correctAnswerNo, List<String> answers) {
        this.question = question;
        this.correctAnswerNo = correctAnswerNo;
        this.answers = answers;
    }
}
