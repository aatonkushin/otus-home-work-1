package org.tonkushin.otushw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.service.QuizService;

import java.util.Scanner;

/**
 * Реализация контроллера для взаимодействия с пользователем
 */
@Service
public class QuizControllerImp implements QuizController {
    private static final String WELCOME_TITLE = "Добро пожаловать в программу по проведению тестирования студентов!\n" +
            "Вам будет представлено 5 вопросов и несколько вариантов ответов для каждого из них.\n" +
            "Выбор ответа производится нажатием соответствующей цифры на клавиатуре.\n" +
            "Для начала тестирования нажмите Enter или введите exit для выхода...";
    private static final String PASSED_TITLE = "ТЕСТ СДАН";
    private static final String FAILED_TITLE = "ТЕСТ НЕ СДАН";
    private static final String WRONG_INPUT_TITLE = "Ошибка ввода - ответ не засчитан";
    private static final String EXIT_CMD = "exit";

    private QuizService service; //бизнес-логика
    private Scanner sc;          //считывает из консоли

    @Autowired
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

        //Задаём вопросы поочерёдно
        while (service.hasNextQuestion()) {
            Question question = service.getNextQuestion();

            System.out.println(question.getQuestion() + ": ");
            for (int i = 0; i < question.getAnswers().size(); i++) {
                System.out.println((i + 1) + ". " + question.getAnswers().get(i));
            }

            Scanner sc = new Scanner(System.in);

            try {
                text = sc.nextLine();

                //Выходим, если пользователь набрал exit
                if (EXIT_CMD.equals(text)) {
                    return;
                }

                //Вычитаем 1, т.к. вопросы для пользователя начинаются с 1, а массив с 0
                question.setUserAnswerNo(Integer.parseInt(text)-1);
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT_TITLE);
            }
        }

        //Выводим результат
        String result = service.getPassed() ? PASSED_TITLE : FAILED_TITLE;
        System.out.println(result);
    }
}
