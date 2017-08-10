package api.factories;


import api.AdParseException;
import api.interfaces.Observer;
import api.interfaces.Settings;

public class ObserverFactory {

    /**
     * Возвращает объект хранилища данных исходя из настроек
     * @param settings настройки
     * @param prefix название
     * @return
     * @throws AdParseException
     */
    public static Observer create(Settings settings, String prefix) throws AdParseException {
        // Создание парсера через рефлексию
        Observer instance;
        try {
            instance = (Observer) Class
                    .forName("api.observers."+prefix + "Observer")
                    .getConstructor(Settings.class)
                    .newInstance(settings);
        } catch (Exception e) {
            throw new AdParseException(e);
        }
        return instance;
    }
}
