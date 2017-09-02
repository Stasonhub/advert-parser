package api.interfaces;

import api.AdParseException;
import api.Advert;
import api.Project;

import java.util.List;

/**
 * Парсер доски объявления
 */
public interface Parser {

    /**
     * Возвращает объявления с нужной страницы
     * @param project - проект
     * @return
     */
    public List<Advert> getAdverts(Project project) throws AdParseException;

}
