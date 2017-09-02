package api.factories;

import api.AdParseException;
import api.SettingsParam;
import api.interfaces.Settings;
import api.interfaces.Storage;

/**
 * Фабрика хранилища
 */
public class StorageFactory {
    /**
     * Возвращает объект хранилища данных исходя из настроек
     * @param settings
     * @return
     * @throws AdParseException
     */
    public static Storage create(Settings settings) throws AdParseException {
        Storage instance;
        try {
            String domainName = settings.get(SettingsParam.STORAGE.toString());
            instance = (Storage) Class.forName("storage."+domainName + "Storage")
                    .getConstructor(Settings.class)
                    .newInstance(settings);
        } catch (Exception e) {
            throw new AdParseException(e);
        }
        return instance;
    }
}
