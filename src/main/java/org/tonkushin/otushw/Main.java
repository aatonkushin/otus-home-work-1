package org.tonkushin.otushw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.tonkushin.otushw.controller.QuizController;

@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableConfigurationProperties
public class Main implements CommandLineRunner {

    private final QuizController controller;

    @Autowired
    public Main(QuizController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        controller.startQuiz();
    }
}
