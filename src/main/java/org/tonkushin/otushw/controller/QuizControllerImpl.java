package org.tonkushin.otushw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.service.MessagesService;
import org.tonkushin.otushw.service.QuizService;

import java.util.Scanner;

/**
 * Реализация контроллера для взаимодействия с пользователем
 */
@Service
public class QuizControllerImpl implements QuizController {

    private QuizService service;                //бизнес-логика
    private MessagesService messagesService;    //сообщения
    private Scanner sc;                         //считывает из консоли
    private boolean testCompleted = false;        //тест выполнен

    @Autowired
    public QuizControllerImpl(QuizService service, MessagesService messagesService) {
        this.service = service;
        this.messagesService = messagesService;
        this.sc = new Scanner(System.in);
    }

    @Override
    public void startQuiz() {
        testCompleted = false;
        //Приветствие пользователя.
        System.out.println(messagesService.getMessage("welcome_title"));
        String text = sc.nextLine();

        //Выходим, если пользователь набрал exit

        if (messagesService.getMessage("exit_cmd").equals(text)) {
            return;
        }

        service.setLocale(messagesService.getLocale());

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
                if (messagesService.getMessage("exit_cmd").equals(text)) {
                    return;
                }

                //Вычитаем 1, т.к. вопросы для пользователя начинаются с 1, а массив с 0
                question.setUserAnswerNo(Integer.parseInt(text) - 1);
            } catch (NumberFormatException e) {

                System.out.println(messagesService.getMessage("wrong_input_title"));
            }
        }

        //Выводим результат
        String result = service.getPassed()
                ? messagesService.getMessage("passed_title")
                : messagesService.getMessage("failed_title");
        System.out.println(result);

        testCompleted = true;
    }

    @Override
    public void selectLanguage() {
        //Выбор языка
        System.out.println(messagesService.getMessage("select_language"));
        System.out.println(messagesService.getAvailableLanguages());

        //Ожидаем ввода пользователя
        String text = sc.nextLine();

        //Создаём локаль в зависимости от ввода пользователя.
        try {
            int n = Integer.parseInt(text);
            messagesService.setLocale(n);
        } catch (Exception e) {
            System.out.println(messagesService.getMessage("lang_not_recognized"));
        }
    }

    @Override
    public void login(String username) {
        service.setUsername(username);
    }

    @Override
    public void results() {
        System.out.println(service.getUsername());
        //Выводим результат
        String result = service.getPassed()
                ? messagesService.getMessage("passed_title")
                : messagesService.getMessage("failed_title");
        System.out.println(result);
    }

    @Override
    public boolean isUserLoggedIn() {
        return service.getUsername() != null && !service.getUsername().isEmpty();
    }

    @Override
    public boolean isTestCompleted() {
        return testCompleted;
    }
}
