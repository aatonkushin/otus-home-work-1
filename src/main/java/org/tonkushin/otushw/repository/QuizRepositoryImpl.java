package org.tonkushin.otushw.repository;

import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.tonkushin.otushw.model.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Реализация сервиса отвечающего за чтение данных из csv файла
 */
public class QuizRepositoryImpl implements QuizRepository {

    private String filename;    //имя файла из которого читаем

    /**
     * Конструктор с параметрами
     *
     * @param filename имя файла для чтения
     */
    public QuizRepositoryImpl(String filename) {
        this.filename = filename;
    }

    public List<Question> readQuestionsFromFile() {
        List<Question> questions = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource(filename);

            try (CSVReader csvReader = new CSVReader(new FileReader(resource.getURI().getPath()), ';')) {
                List<String[]> myEntries = csvReader.readAll();

                for (String[] line : myEntries) {
                    Question q = new Question();
                    //Вопрос
                    q.setQuestion(line[0]);
                    //Правильный ответ
                    q.setCorrectAnswerNo(Integer.parseInt(line[1]));
                    //Варианты ответов (все оставшиеся колонки в строке)
                    List<String> answers = new ArrayList<>(Arrays.asList(line).subList(2, line.length));
                    q.setAnswers(answers);

                    questions.add(q);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
