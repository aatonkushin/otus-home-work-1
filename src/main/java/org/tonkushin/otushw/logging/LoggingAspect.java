package org.tonkushin.otushw.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Locale;

/**
 * Сделано для получения навыков AOP
 */

@Aspect
@Component
public class LoggingAspect {
    private StopWatch stopWatch;                    //Для контроля времени загрузки
    private final MessageSource messageSource;      //для вывода сообщений на языке пользователя
    private Locale locale;                          //язык сообщений

    @Autowired
    public LoggingAspect(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Before("execution(* org.tonkushin.otushw.repository.QuizRepositoryImpl.readQuestionsFromFile(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] objs = joinPoint.getArgs();

        //Всего один параметр - локаль
        if (objs.length > 0) {
            locale = Locale.forLanguageTag(objs[0].toString());
        } else {
            locale = Locale.getDefault();
        }

        System.out.print(messageSource.getMessage("loading", new Object[]{}, locale));
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    @After("execution(* org.tonkushin.otushw.repository.QuizRepositoryImpl.readQuestionsFromFile(..))")
    public void logAfter() {
        stopWatch.stop();
        System.out.println(messageSource.getMessage("loading_done", new Object[]{stopWatch.getTotalTimeSeconds()}, locale));
    }
}
