package api.interfaces;

import api.AdParseException;
import api.Advert;
import api.Project;

import java.util.List;

/**
 * Отвечает за хранение и выдачу данных программы
 */
public interface Storage {

    /**
     * Ищет объявление
     * @param advert объявление
     */
    Advert findAdvertByUrl(Advert advert) throws AdParseException;

    /**
     * Сохраняет объявление
     * @param advert - объявление
     */
    void save(Advert advert) throws AdParseException;

    /**
     * Обновляет объявление
     * @param advert - объявление
     * @return
     */
    void update(Advert advert) throws AdParseException;


    /**
     * Создает проект
     * @param project - проект
     * @return - id созданного проекта
     * @throws AdParseException
     */
    Integer createProject(Project project) throws AdParseException;


    /**
     * Возвращает список всех проектов
     * @return
     * @throws AdParseException
     */
    List<Project> listAllProjects() throws AdParseException;

    /**
     * Удаляет проект по его идентификатору
     * @param id
     * @throws AdParseException
     */
    void deleteProjectWithAdverts(int id) throws AdParseException;

}
