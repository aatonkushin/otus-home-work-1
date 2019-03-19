package org.tonkushin.otushw.service;

import java.util.Locale;

/**
 * Отвечает за работу со строковыми ресурсами
 */
public interface MessagesService {
    /**
     * Устанавливает текущую локаль
     * @param locale локаль
     */
    void setLocale(Locale locale);

    /**
     * Устанавливает локаль по номеру из списка
     * @param n - номер локали в списке getAvailableLanguages();
     */
    void setLocale(int n) throws Exception;

    /**
     * Возвращает текущую локаль
     * @return локаль
     */
    Locale getLocale();

    /**
     * Возвращает доступные языки
     * @return список доступных языков
     */
    String getAvailableLanguages();

    /**
     * Возвращает сообщение с указанным именем из bundle.properties
     * @param messageName имя сообщения
     * @return текст сообщения
     */
    String getMessage(String messageName);

    /**
     * Возвращает сообщение с указанным именем из bundle.properties
     * @param messageName Возвращает сообщение с указанным именем из bundle.properties
     * @param params параметры для отображения
     * @return текст сообщения
     */
    String getMessage(String messageName, String[] params);
}
