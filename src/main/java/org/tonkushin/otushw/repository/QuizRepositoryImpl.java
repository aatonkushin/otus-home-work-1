package org.tonkushin.otushw.repository;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.tonkushin.otushw.model.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Реализация сервиса отвечающего за чтение данных из csv файла
 */
public class QuestionsReaderServiceImpl implements QuizRepository {

    private String filename;    //имя файла из которого читаем

    private String[] columns;   //поля класса Question, они же колонки csv-файла

    /**
     * Конструктор с параметрами
     * @param filename имя файла для чтения
     * @param columns поля класса Question, они же колонки csv-файла
     */
    public QuestionsReaderServiceImpl(String filename, String[] columns) {
        this.filename = filename;
        this.columns = columns;
    }

    public List<Question> readQuestionsFromFile() {
        ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<Question>();
        strategy.setType(Question.class);
        strategy.setColumnMapping(columns);
        CsvToBean csv = new CsvToBean();

        List<Question> questions = null;
        try {
            Resource resource = new ClassPathResource(filename);

            try (CSVReader csvReader = new CSVReader(new FileReader(resource.getURI().getPath()), ';');) {
                questions = csv.parse(strategy, csvReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
