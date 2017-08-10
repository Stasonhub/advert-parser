package api.factories;

import api.AdParseException;
import api.UrlParser;
import api.interfaces.Parser;


/**
 * Создает класс конкретного парсера исходя из ссылки, т.е. если мы
 * укажем ссылку на авито в конструкторе класс вернет парсер для авито
 */
public class ParserFactory {
    /**
     * Возвращает объект парсера исходя из ссылки
     * @param domain - ссылка на ресурс
     * @return
     * @throws AdParseException
     */
    public static Parser create(String domain) throws AdParseException {
        // Создание парсера через рефлексию
        Parser instance;
        try {
            UrlParser urlParser = new UrlParser();
            String className ="parser."+ urlParser.getFirstLevelDomain(domain);
            instance = (Parser)Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new AdParseException(e);
        }
        return instance;
    }

}
