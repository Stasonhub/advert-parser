package parser.dateconverter;


import api.AdParseException;
import api.UrlParser;

public class Factory {
    /**
     * Возвращает объект парсера исходя из ссылки
     * @param domain - ссылка на ресурс
     * @param date - дата для конвертации
     * @return
     * @throws AdParseException
     */
    public static DateConverter create(String domain, String date) throws AdParseException {
        // Создание парсера через рефлексию
        DateConverter instance;
        try {
            UrlParser urlParser = new UrlParser();
            String className ="parser.dateconverter."+ urlParser.getFirstLevelDomain(domain);
            instance = (DateConverter) Class.forName(className)
                    .getConstructor(String.class)
                    .newInstance(date);
        } catch (Exception e) {
            throw new AdParseException(e);
        }
        return instance;
    }

}
