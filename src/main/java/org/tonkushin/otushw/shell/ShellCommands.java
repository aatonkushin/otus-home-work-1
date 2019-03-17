package org.tonkushin.otushw.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.tonkushin.otushw.model.Question;
import org.tonkushin.otushw.service.QuizService;

import javax.annotation.PostConstruct;
import java.util.Locale;

@ShellComponent
public class ShellCommands {
    private final QuizService service;
    private final MessageSource ms;                 //сообщения
    private Locale locale;                          //локаль
    private String[] availableLanguages;            //доступные языки

    private boolean chooseLanguageMode = false;     //выбор языка
    private boolean chooseOptionMode = false;        //выбор ответа на вопрос

    private Question currentQuestion;               //текущий вопрос

    @Autowired
    public ShellCommands(QuizService service, MessageSource ms, @Value("${available_languages}") String[] availableLanguages) {
        this.service = service;
        this.ms = ms;
        this.availableLanguages = availableLanguages;
        locale = Locale.getDefault();
    }

    @PostConstruct
    private void postConstruct() {
        chooseLanguageMode = true;
        System.out.println(ms.getMessage("select_language", new String[]{}, locale));

        StringBuilder sb = new StringBuilder();
        getAvailLanguagesString(sb);

        System.out.println(sb.toString());
    }


    @ShellMethod("Запускает тестирование")
    public String start() {
        chooseLanguageMode = false;
        chooseOptionMode = true;

        //Задаём вопросы поочерёдно
        if (service.hasNextQuestion()) {
            currentQuestion = service.getNextQuestion();
            return currentQuestion.getQuestionAndAnswers();
        }

        return "";
    }

    //Делаем команду на каждую доступную опцию при выборе языка или ответа.
    //Не очень красиво, но так и не нашёл как в Spring Shell сделать выбор опций правильно.
    @ShellMethod(value = "Выбор первого пункта из предложенного списка", key = "1")
    @ShellMethodAvailability("chooseOptionAvailability")
    public String chooseOptionOne() throws Exception {
        return processOption(1);
    }

    @ShellMethod(value = "Выбор второго пункта из предложенного списка", key = "2")
    @ShellMethodAvailability("chooseOptionAvailability")
    public String chooseOptionTwo() throws Exception {
        return processOption(2);
    }

    @ShellMethod(value = "Выбор третьего пункта из предложенного списка", key = "3")
    @ShellMethodAvailability("chooseOptionAvailability")
    public String chooseOptionThree() throws Exception {
        return processOption(3);
    }

    @ShellMethod(value = "Выбор любого пункта из списка", key = {"answer", "a"})
    @ShellMethodAvailability("chooseOptionAvailability")
    public String chooseAnyOption(int n) throws Exception {
        return processOption(n);
    }

    @ShellMethod(value = "Выбор языка интерфейса")
    public String lang() {
        chooseLanguageMode = true;
        StringBuilder retVal = new StringBuilder();
        retVal.append(ms.getMessage("select_language", new String[]{}, locale)).append("\n");
        getAvailLanguagesString(retVal);

        return retVal.toString();
    }

    //Доступность команды выбора языка
    private Availability langAvailability() {
        return !chooseOptionMode
                ? Availability.available()
                : Availability.unavailable(ms.getMessage("test_in_progress", new String[]{}, locale));
    }

    //Доступность команды выбора номера ответа на вопрос
    private Availability chooseOptionAvailability() {
        return chooseLanguageMode || chooseOptionMode ? Availability.available()
                : Availability.unavailable(ms.getMessage("nothing_to_coose", new String[]{}, locale));
    }

    //Обработка выбранного номера ответа
    private String processOption(int n) throws Exception {
        //Выбор языка
        if (chooseLanguageMode) {
            chooseLanguageMode = false;
            locale = Locale.forLanguageTag(availableLanguages[n - 1]);
            return ms.getMessage("welcome_title", new String[]{}, locale);
        }

        //Выбор ответа на вопрос
        if (chooseOptionMode) {
            currentQuestion.setUserAnswerNo(n - 1);
            if (service.hasNextQuestion()) {
                currentQuestion = service.getNextQuestion();
                return currentQuestion.getQuestionAndAnswers();
            } else {
                return service.getPassed()
                        ? ms.getMessage("passed_title", new String[]{}, locale)
                        : ms.getMessage("failed_title", new String[]{}, locale);
            }
        }

        throw new Exception("Недопустимая операция");
    }

    private void getAvailLanguagesString(StringBuilder sb) {
        for (int i = 0; i < availableLanguages.length; i++) {
            String tag = availableLanguages[i];
            String langName = Locale.forLanguageTag(tag).getDisplayLanguage(Locale.forLanguageTag(tag));
            sb.append(i + 1).append(". ").append(langName).append("\n");
        }
    }
}
