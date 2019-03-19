package org.tonkushin.otushw.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.tonkushin.otushw.controller.QuizController;
import org.tonkushin.otushw.service.MessagesService;

@ShellComponent
public class ShellCommands {
    private final QuizController controller;        //контроллер
    private final MessagesService messagesService;  //сообщения

    @Autowired
    public ShellCommands(QuizController controller, MessagesService messagesService) {
        this.controller = controller;
        this.messagesService = messagesService;
    }


    @ShellMethod("Запускает тестирование")
    public String start() {
        controller.startQuiz();
        return messagesService.getMessage("test_complete");
    }

    @ShellMethod(value = "Выбор языка интерфейса")
    public String lang() {
        controller.selectLanguage();
        String[] params = new String[]{messagesService.getLocale().getDisplayLanguage(messagesService.getLocale())};
        return messagesService.getMessage("selected_language", params);
    }

    @ShellMethod(value = "Вход пользователя")
    public String login(String name) {
        controller.login(name);
        String[] params = new String[]{name};
        return messagesService.getMessage("user_logged_in", params);
    }
}
