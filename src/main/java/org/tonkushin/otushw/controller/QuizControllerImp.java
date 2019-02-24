package org.tonkushin.otushw.controller;

import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.service.QuizService;

import java.util.Scanner;

/**
 * Реализация контроллера для взаимодействия с пользователем
 */
public class QuizControllerImp implements QuizController {

    private QuizService service; //бизнес-логика
    private Scanner sc;          //считывает из консоли

    private final String welcomeTitle= "Добро пожаловать в программу по проведению тестирования студентов!\n"+
            "Вам будет представлено 5 вопросов и три варианта ответов для каждого из них.\n" +
            "Выбор ответа производится нажатием соответствующей цифры на клавиатуре.\n" +
            "Для начала тестирования нажмите Enter или введите exit для выхода...";

    private final String passedTitle="ТЕСТ СДАН";
    private final String failedTitle = "ТЕСТ НЕ СДАН";
    private final String wrongInputTitle = "Ошибка ввода - ответ не засчитан";


    public QuizControllerImp(QuizService service) {
        this.service = service;
        sc = new Scanner(System.in);
    }

    @Override
    public void startQuiz() {
//        System.out.println("Добро пожаловать в программу по проведению тестирования студентов!");
//        System.out.println("Вам будет представлено 5 вопросов и три варианта ответов для каждого из них. " +
//                "Выбор ответа производится нажатием соответствующей цифры на клавиатуре.");
//        System.out.println("Для начала тестирования нажмите любую клавишу или введите exit для выхода...");
        System.out.println(welcomeTitle);
        String text = sc.nextLine();

        //Выходим, если пользователь набрал exit
        if (text.equals("exit")) {
            return;
        }

        while (service.hasNextQuestion()) {
            Question question = service.getNextQuestion();

            System.out.println(question.getQuestion() + ": ");
            System.out.println("1. " + question.getAnswer1());
            System.out.println("2. " + question.getAnswer2());
            System.out.println("3. " + question.getAnswer3());

            Scanner sc = new Scanner(System.in);

            try {
                text = sc.nextLine();

                //Выходим, если пользователь набрал exit
                if (text.equals("exit")) {
                    return;
                }

                question.setUserAnswerNo(Integer.parseInt(text));
            } catch (NumberFormatException e) {
                System.out.println(wrongInputTitle);
            }
        }

        String result = service.getPassed() ? passedTitle : failedTitle;
        System.out.println(result);
    }
}
