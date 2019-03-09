package org.tonkushin.otushw;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.tonkushin.otushw.controller.QuizController;

@ComponentScan
@Configuration
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuizController controller = context.getBean(QuizController.class);
        controller.startQuiz();
    }
}
