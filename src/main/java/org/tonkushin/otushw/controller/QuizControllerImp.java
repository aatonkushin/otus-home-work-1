package org.tonkushin.otushw.controller;

import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.repository.QuizRepository;

import java.util.List;
import java.util.Scanner;

/**
 * Реализация контроллера для взаимодействия с пользователем
 */
public class ConsoleControllerImpl implements ConsoleController {

    private QuizRepository service; //отвечает за чтение CSV-файла

    private Scanner sc;                             //считывает из консоли

    public ConsoleControllerImpl(QuizRepository service) {
        this.service = service;
        sc = new Scanner(System.in);
    }

    @Override
    public void startQuiz() {
        System.out.println("Добро пожаловать в программу по проведению тестирования студентов!");
        System.out.println("Вам будет представлено 5 вопросов и три варианта ответов для каждого из них. " +
                "Выбор ответа производится нажатием соответсвующей цифры на клавиатуре.");
        System.out.println("Для начала тестирования нажмите любую клавишу или введите exit для выхода...");
        String text = sc.nextLine();

        //Выходим, если пользователь набрал exit
        if (text.equals("exit")){
            return;
        }

        //Читаем данные из файла.
        List<Question> questions = service.readQuestionsFromFile();

        //Задаём вопросы поочерёдно
        for (Question question : questions) {
            System.out.println(question.getQuestion() + ": ");
            System.out.println("1. " + question.getAnswer1());
            System.out.println("2. " + question.getAnswer2());
            System.out.println("3. " + question.getAnswer3());

            Scanner sc = new Scanner(System.in);

            try{
                text = sc.nextLine();

                //Выходим, если пользователь набрал exit
                if (text.equals("exit")){
                    return;
                }

                question.setUserAnswerNo(Integer.parseInt(text));
            } catch (NumberFormatException e){
                System.out.println("Ошибка ввода - ответ не засчитан");
            }
        }

        System.out.println("Результат");
        long count = questions.stream().filter(Question::isCorrect).count();
        System.out.println("Правильных ответов: "+count +" из "+ questions.size());
    }
}
