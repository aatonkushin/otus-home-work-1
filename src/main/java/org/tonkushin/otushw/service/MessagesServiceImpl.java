package org.tonkushin.otushw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Реализация сервиса для работы со строковыми ресурсами (сообщениями).
 */
@Service
public class MessagesServiceImpl implements MessagesService {
    private final MessageSource messageSource;
    private final String[] availableLanguages;      //доступные языки

    private Locale locale = Locale.getDefault();    //устанавливаем локаль по-умолчанию.

    @Autowired
    public MessagesServiceImpl(MessageSource messageSource, @Value("${available_languages}") String[] availableLanguages) {
        this.messageSource = messageSource;
        this.availableLanguages = availableLanguages;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void setLocale(int n) throws Exception {
        if (n <= availableLanguages.length && n > 0)
            this.locale = Locale.forLanguageTag(availableLanguages[n - 1]);
        else
            throw new Exception(messageSource.getMessage("lang_not_recognized", new String[]{}, locale));
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public String getAvailableLanguages() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < availableLanguages.length; i++) {
            String tag = availableLanguages[i];
            String langName = Locale.forLanguageTag(tag).getDisplayLanguage(Locale.forLanguageTag(tag));
            sb.append(i + 1).append(". ").append(langName).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getMessage(String messageName) {
        return this.getMessage(messageName, new String[]{});
    }

    @Override
    public String getMessage(String messageName, String[] params) {
        return messageSource.getMessage(messageName, params, locale);
    }
}
