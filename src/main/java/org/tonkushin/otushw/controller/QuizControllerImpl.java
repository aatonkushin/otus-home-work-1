package org.tonkushin.otushw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.service.QuizService;

import java.util.Locale;
import java.util.Scanner;

/**
 * Реализация контроллера для взаимодействия с пользователем
 */
@Service
public class QuizControllerImpl implements QuizController {

    private QuizService service;    //бизнес-логика
    private MessageSource ms;       //сообщения
    private Scanner sc;             //считывает из консоли
    private Locale locale;          //локаль2
    private String[] avilableLanguages;     //доступные языкия

    @Autowired
    public QuizControllerImpl(QuizService service, MessageSource ms, @Value("${available_languages}") String[] avilableLanguages) {
        this.service = service;
        this.ms = ms;
        this.sc = new Scanner(System.in);
        this.avilableLanguages = avilableLanguages;
        this.locale = Locale.getDefault();
    }

    @Override
    public void startQuiz() {
        //Выбор языка
        System.out.println(ms.getMessage("select_language", new String[]{}, locale));

        //Перечисляем доступные языки
        for (int i = 0; i < avilableLanguages.length; i++) {
            String tag = avilableLanguages[i];
            System.out.println((i + 1) + ". " + Locale.forLanguageTag(tag).getDisplayLanguage(Locale.forLanguageTag(tag)));
        }

        //Ожидаем ввода пользователя
        String text = sc.nextLine();

        //Создаём локаль в зависимости от ввода пользователя.
        try {
            int n = Integer.parseInt(text);
            locale = Locale.forLanguageTag(avilableLanguages[n - 1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(ms.getMessage("lang_not_recognized", new String[]{}, locale));
            return;
        }

        //Приветствие пользователя.
        System.out.println(ms.getMessage("welcome_title", new String[]{}, locale));
        text = sc.nextLine();

        //Выходим, если пользователь набрал exit
        if (ms.getMessage("exit_cmd", new String[]{}, locale).equals(text)) {
            return;
        }

        service.setLocale(locale);

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
                if (ms.getMessage("exit_cmd", new String[]{}, locale).equals(text)) {
                    return;
                }

                //Вычитаем 1, т.к. вопросы для пользователя начинаются с 1, а массив с 0
                question.setUserAnswerNo(Integer.parseInt(text) - 1);
            } catch (NumberFormatException e) {
                System.out.println(ms.getMessage("wrong_input_title", new String[]{}, locale));
            }
        }

        //Выводим результат
        String result = service.getPassed()
                ? ms.getMessage("passed_title", new String[]{}, locale)
                : ms.getMessage("failed_title", new String[]{}, locale);
        System.out.println(result);
    }
}
