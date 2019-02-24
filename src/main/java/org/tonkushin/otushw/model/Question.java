package org.tonkushin.otushw.model;

import java.util.List;

/**
 * Класс сущности, представляющей вопрос и варианты ответов на него
 */
public class Question {
    private String question;            //вопрос
    private String answer1;             //вариант ответа 1
    private String answer2;             //вариант ответа 2
    private String answer3;             //вариант ответа 3
    private Integer correctAnswerNo;    //номер правильного ответа
    private Integer userAnswerNo = -1;  //ответ пользователя

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
     * вариант ответа 1
     * @return вариант ответа 1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * вариант ответа 1
     * @param answer1 вариант ответа 1
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * вариант ответа 2
     * @return вариант ответа 2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * вариант ответа 2
     * @param answer2 вариант ответа 2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * вариант ответа 3
     * @return вариант ответа 3
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * вариант ответа 3
     * @param answer3 вариант ответа 3
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
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
     * @param answer1 ответ 1
     * @param answer2 ответ 2
     * @param answer3 ответ 3
     * @param correctAnswerNo номер правильного ответа
     */
    public Question(String question, String answer1, String answer2, String answer3, Integer correctAnswerNo) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswerNo = correctAnswerNo;
    }
}
