package org.tonkushin.otushw;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tonkushin.otushw.controller.QuizController;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizController controller = context.getBean(QuizController.class);
        controller.startQuiz();
    }
}
