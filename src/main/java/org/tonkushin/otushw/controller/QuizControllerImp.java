package org.tonkushin.otushw.controller;

import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.service.QuizService;

import java.util.Scanner;

/**
 * Реализация контроллера для взаимодействия с пользователем
 */
public class QuizControllerImp implements QuizController {
    private static final String WELCOME_TITLE= "Добро пожаловать в программу по проведению тестирования студентов!\n"+
            "Вам будет представлено 5 вопросов и три варианта ответов для каждого из них.\n" +
            "Выбор ответа производится нажатием соответствующей цифры на клавиатуре.\n" +
            "Для начала тестирования нажмите Enter или введите exit для выхода...";

    private static final String PASSED_TITLE="ТЕСТ СДАН";
    private static final String FAILED_TITLE = "ТЕСТ НЕ СДАН";
    private static final String WRONG_INPUT_TITLE = "Ошибка ввода - ответ не засчитан";
    private static final String EXIT_CMD = "exit";

    private QuizService service; //бизнес-логика
    private Scanner sc;          //считывает из консоли

    public QuizControllerImp(QuizService service) {
        this.service = service;
        sc = new Scanner(System.in);
    }

    @Override
    public void startQuiz() {
        System.out.println(WELCOME_TITLE);
        String text = sc.nextLine();

        //Выходим, если пользователь набрал exit
        if (EXIT_CMD.equals(text)) {
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
                if (EXIT_CMD.equals(text)) {
                    return;
                }

                question.setUserAnswerNo(Integer.parseInt(text));
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT_TITLE);
            }
        }

        String result = service.getPassed() ? PASSED_TITLE : FAILED_TITLE;
        System.out.println(result);
    }
}
